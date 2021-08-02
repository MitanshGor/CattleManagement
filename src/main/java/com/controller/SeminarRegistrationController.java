package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.ResponseBeanWithList;
import com.bean.SeminarBean;
import com.bean.SeminarRegistrationBean;
import com.bean.SeminarRegistrationMailBean;
import com.bean.UserBean;
import com.dao.SeminarDao;
import com.dao.SeminarRegistrationDao;
import com.dao.SeminarRegistrationMailDao;
import com.dao.UserDao;
import com.service.EmailService;
//import com.service.WhatsappService;

@RestController
public class SeminarRegistrationController {
	
	@Autowired
	SeminarRegistrationDao seminarRegisterDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	SeminarDao seminarDao;
	
	//@Autowired
	//WhatsappService whatsappService;
	
	@Autowired
	SeminarRegistrationMailDao seminarMailDao;

	@PostMapping("/registerSeminar")
	public ResponseBean<SeminarRegistrationBean> registerSeminar(SeminarRegistrationBean registerBean){
		ResponseBean<SeminarRegistrationBean> rb = new ResponseBean<SeminarRegistrationBean>(); 
		UserBean user = userDao.getUserByEmailID(registerBean.getEmailID());
		registerBean.setUserID(user.getUserID());
		if(seminarRegisterDao.checkIfUserIsRegistered(registerBean.getUserID(), registerBean.getSeminarID())) {
			int r = seminarRegisterDao.registerSeminar(registerBean);
			rb.setData(registerBean);
			if(r == 1) {
				rb.setMessage("Registeration Successful");
				SeminarRegistrationMailBean message = seminarMailDao.getMessage();
				String formattedMessage = message.getBody().replaceAll("{0}", user.getFirstName() +" "+ user.getLastName());				
				message.setBody(formattedMessage);		
				emailService.sendRegisterationMail(user,message);
				//whatsappService.sendRegisterationWhatsapp(user,message);
				rb.setStatus(200);
			}
			else {
				rb.setMessage("Registeration Unsuccessful");
				rb.setStatus(-1);
			}	
		}
		else {
			rb.setMessage("You have Already Registered For this Seminar");
			rb.setStatus(-1);
		}
		return rb;
	}
	@GetMapping("/getActiveSeminarList")
	public ResponseBeanWithList<SeminarBean> getActiveSemianrList(){
		ResponseBeanWithList<SeminarBean> rb = new ResponseBeanWithList<SeminarBean>();
		List<SeminarBean> data = seminarDao.getActiveSeminar(); 
		if(data != null) {
			rb.setMessage("Succesfully Fetched");
			rb.setData(data);
			rb.setStatus(200);
		}
		else {
			rb.setMessage("Some Error Occured");
			rb.setData(data);
			rb.setStatus(-1);
		}
		return rb;
	}
}
