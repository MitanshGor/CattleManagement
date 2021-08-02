package com.jspController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bean.AdminBean;
import com.bean.LinkBean;
import com.bean.SeminarBean;
import com.bean.SeminarRegisteredUsers;
import com.dao.AdminDao;
import com.dao.CounsellingCancellationMailDao;
import com.dao.CounsellingRegistrationMailDao;
import com.dao.LinkDao;
import com.dao.SeminarDao;
import com.dao.SeminarRegistrationDao;
import com.dao.SeminarRegistrationMailDao;
import com.service.GoogleDriveService;


@Controller
@RequestMapping("/admin")
public class JSPAdminController {
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	LinkDao linkDao;
	
	@Autowired
	GoogleDriveService driveService;
	
	@Autowired
	SeminarDao seminarDao;
	
	@Autowired
	SeminarRegistrationDao seminarRegistrationDao;

	
	@GetMapping("/adminDashboard")
	public String getAdminDashboard() {
		return "AdminDashboard";
	}

	@GetMapping("/seminarManagement")
	public String getSeminarMangement(Model model,HttpSession session) {
		String  msg = null;
		try{
			msg = (String)session.getAttribute("msg");
			session.removeAttribute("msg");			
		}
		catch(Exception e) {
			
		}
		model.addAttribute("msg",msg);
		model.addAttribute("seminarList",seminarDao.getAllSeminar());
		return "AdminSeminarManagement";
	}
	@GetMapping("/linkManagement")
	public String getLinkTemplate(Model model,HttpSession session) {
		String  msg = null;
		try{
			msg = (String)session.getAttribute("msg");
		}
		catch(Exception e) {
			
		}
		session.removeAttribute("msg");
		//System.out.println("Message "+msg);
		model.addAttribute("msg",msg);
		model.addAttribute("link",linkDao.getAllLinks().get(0));
		return "AdminLinkTemplate";
	}
	@PostMapping("/updateLinks")
	public String updateLinks(LinkBean link,HttpSession session) {
		int a = linkDao.updateLinks(link);
		if (a == 1){
			session.setAttribute("msg", "Updated Links Successfully");			
		}
		else {
			session.setAttribute("msg", "Some Error Occured");				
		}
		return "redirect:/admin/linkManagement";
	}
	@GetMapping("/adminManagment")
	public String adminMangment(Model model,HttpSession session) {
		String  msg = null;
		try{
			msg = (String)session.getAttribute("msg");
		}
		catch(Exception e) {
			
		}
		session.removeAttribute("msg");
		//System.out.println("Message "+msg);
		model.addAttribute("msg",msg);
		model.addAttribute("admins", adminDao.getAllAdmin());
		return "AdminManagmentDashboard";
	}
	@GetMapping("/loginForm")
	public String loginForm() {
		return "LoginForm";
	}
	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "ForgotPassword";
	}
	
	@PostMapping("/addAdmin")
	public String addAdmin(AdminBean admin,Model model,HttpSession session) {
		if(adminDao.checkDuplicateEmail(admin.getEmailID())) {
			LocalDateTime ct = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
			admin.setCreatedAt(Timestamp.valueOf(ct));  
			int a = adminDao.addAdmin(admin);
			if (a == 1){
				session.setAttribute("msg", "Added Admin Successfully");			
			}
			else {
				session.setAttribute("msg", "Some Error Occured");				
			}
		}
		else {
			session.setAttribute("msg","Email ID already Existed");
		}
		return "redirect:/admin/adminManagment";
	}
	
	@GetMapping("/deleteAdmin/{adminID}")
	public String addAdmin(@PathVariable int adminID,HttpSession session) {
		int a = adminDao.deleteAdmin(adminID);
		if (a == 1){
				session.setAttribute("msg", "Deleted Admin Successfully");			
			}
		else {
				session.setAttribute("msg", "Some Error Occured");				
			}
		return "redirect:/admin/adminManagment";
	}
	
	@GetMapping("/addSeminar")
	public String addSeminar(Model model,HttpSession session) {
		model.addAttribute("seminarLink",linkDao.getAllLinks().get(0).getSeminarLink());
		try {
			SeminarBean seminar = (SeminarBean)session.getAttribute("seminar");
			if(seminar==null) {
				model.addAttribute("seminar",new SeminarBean());		
			}
			else {
				model.addAttribute("seminar", seminar);					
			}
			session.removeAttribute("seminar");
		}
		catch(Exception e){
			model.addAttribute("seminar",new SeminarBean());				
		}
		return "AdminAddSeminar";
	}
	
	@PostMapping("/addSeminar")
	public String addSeminarMethod(SeminarBean seminarBean,HttpSession session,@RequestParam("seminarEnglishBanner") MultipartFile englishBanner,  @RequestParam("seminarGujaratiBanner") MultipartFile gujaratiBanner) {
		if(!englishBanner.isEmpty()) {
			String id = driveService.upLoadFile(englishBanner, "seminar");
			seminarBean.setImgPathEnglish("https://drive.google.com/uc?export=view&id="+id);
		}
		if(!gujaratiBanner.isEmpty()) {
			String id = driveService.upLoadFile(gujaratiBanner, "seminar");
			seminarBean.setImgPathGujarati("https://drive.google.com/uc?export=view&id="+id);
		}
		int i = seminarDao.addSeminar(seminarBean);
		if(i == 1) {
			session.setAttribute("msg", "Added Seminar Successfully");
		}
		else {
			session.setAttribute("msg", "Some error occured");
		}
		return "redirect:/admin/seminarManagement";
	}
	@GetMapping("/editSeminar/{seminarID}")
	public String editSeminar(Model model,@PathVariable("seminarID") int seminarID,HttpSession session) {
		SeminarBean seminar = seminarDao.getSeminarByID(seminarID);
		if(seminar != null) {
			session.setAttribute("seminar",seminar);
			
		}else {
			model.addAttribute("msg","Invalid Seminar Page Accesss");
			return "redirect:/admin/seminarManagement";
		}
		return "redirect:/admin/addSeminar";
	}
	
	@PostMapping("/updateSeminar")
	public String editSeminarMethod(SeminarBean seminarBean,HttpSession session,@RequestParam("seminarEnglishBanner") MultipartFile englishBanner,  @RequestParam("seminarGujaratiBanner") MultipartFile gujaratiBanner) {
		
		if(!englishBanner.isEmpty()) {
			String id = driveService.upLoadFile(englishBanner, "seminar");
			seminarBean.setImgPathEnglish("https://drive.google.com/uc?export=view&id="+id);
		}
		if(!gujaratiBanner.isEmpty()) {
			String id = driveService.upLoadFile(gujaratiBanner, "seminar");
			seminarBean.setImgPathGujarati("https://drive.google.com/uc?export=view&id="+id);
		}
		int i = seminarDao.updateSeminar(seminarBean);
		if(i == 1) {
			session.setAttribute("msg", "Edited Seminar Successfully");
		}
		else {
			session.setAttribute("msg", "Some error occured");
		}
		return "redirect:/admin/seminarManagement";
	}
	
	@GetMapping("/viewSeminar/{seminarID}")
	public String viewSeminarDetail(@PathVariable("seminarID") int seminarID,HttpSession session) {
		SeminarBean seminar = seminarDao.getSeminarByID(seminarID);
		if(seminar != null) {
			session.setAttribute("seminar",seminar);
		}else {
			session.setAttribute("msg","Invalid Seminar Page Accesss");
			return "redirect:/admin/seminarManagement";
		}
		return "redirect:/admin/viewSeminarInformation";
	}
	@GetMapping("/viewSeminarRegisteredUser/{seminarID}")
	public String viewSeminarRegisteredUser(@PathVariable("seminarID") int seminarID,HttpSession session) {
		SeminarBean seminar = seminarDao.getSeminarByID(seminarID);
		if(seminar != null) {
			session.setAttribute("seminar",seminar);
			session.setAttribute("registeredUsers", seminarRegistrationDao.getAllRegisteredUsers(seminarID));
		}else {
			session.setAttribute("msg","Invalid Seminar Page Accesss");
			return "redirect:/admin/seminarManagement";
		}
		return "redirect:/admin/viewSeminarRegisteredUsers";
	}
	@GetMapping("/viewSeminarInformation")
	public String viewSeminar(HttpSession session,Model model) {
		try {
			SeminarBean seminar = (SeminarBean)session.getAttribute("seminar");
			if(seminar==null) {
				return "redirect:/admin/seminarManagement";				
			}
			model.addAttribute("seminar",seminar);
			session.removeAttribute("seminar");
			session.removeAttribute("registeredUsers");
		}catch(Exception e) {
			return "redirect:/admin/seminarManagement";
		}
		return "AdminViewSeminar";
	}

	@GetMapping("/viewSeminarRegisteredUsers")
	public String viewSeminarRegisteredUsers(HttpSession session,Model model) {
		try {
			SeminarBean seminar = (SeminarBean)session.getAttribute("seminar");
			@SuppressWarnings("unchecked")
			List<SeminarRegisteredUsers> registeredUsers = (List<SeminarRegisteredUsers>) session.getAttribute("registeredUsers");
			if(seminar==null) {
				return "redirect:/admin/seminarManagement";			
			}
			model.addAttribute("seminar",seminar);
			model.addAttribute("registeredUsers",registeredUsers);
			session.removeAttribute("seminar");
			session.removeAttribute("registeredUsers");
		}catch(Exception e) {
			return "redirect:/admin/seminarManagement";
		}
		return "AdminViewSeminarRegisteredUsers";
	}

}
