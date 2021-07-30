package com.jspController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.AdminBean;
import com.bean.LinkBean;
import com.bean.SeminarBean;
import com.dao.AdminDao;
import com.dao.LinkDao;
import com.dao.UserDao;


@Controller
@RequestMapping("/admin")
public class JSPAdminController {
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	LinkDao linkDao;
	
	@GetMapping("/adminDashboard")
	public String getAdminDashboard() {
		return "AdminDashboard";
	}
	@GetMapping("/messageTemplates")
	public String getMessagesTemplates(Model model,HttpSession session) {
		String  msg = null;
		try{
			msg = (String)session.getAttribute("msg");
		}
		catch(Exception e) {
			
		}
		session.removeAttribute("msg");
		model.addAttribute("msg",msg);
		return "AdminMessageTemplate";
	}
	@GetMapping("/seminarManagement")
	public String getSeminarMangement(Model model,HttpSession session) {
		String  msg = null;
		try{
			msg = (String)session.getAttribute("msg");
		}
		catch(Exception e) {
			
		}
		session.removeAttribute("msg");
		model.addAttribute("msg",msg);
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
	public String addSeminar(Model model) {
		model.addAttribute("seminar",new SeminarBean());
		return "AdminAddSeminar";
	}
	
}
