package com.controller;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
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

	// @Autowired
	// WhatsappService whatsappService;

	@Autowired
	SeminarRegistrationMailDao seminarMailDao;

	@PostMapping("/registerSeminar")
	public ResponseBean<SeminarRegistrationBean> registerSeminar(SeminarRegistrationBean registerBean) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ResponseBean<SeminarRegistrationBean> rb = new ResponseBean<SeminarRegistrationBean>();
		System.out.println("Email ID : "+registerBean.getEmailID());
		UserBean user = userDao.getUserByEmailID(registerBean.getEmailID());
		if (user != null) {
			registerBean.setUserID(user.getUserID());
			SeminarBean seminar = seminarDao.getSeminarByID(registerBean.getSeminarID());			
			if (seminarRegisterDao.checkIfUserIsRegistered(registerBean.getUserID(), registerBean.getSeminarID())) {
				int r = seminarRegisterDao.registerSeminar(registerBean);
				//int  r =1;
				rb.setData(registerBean);
				if (r == 1) {
					rb.setMessage("Registeration Successful");
					SeminarRegistrationMailBean message = seminarMailDao.getMessage();
					Map<String, String> values = new HashMap<String, String>();
					values.put("user name", user.getFirstName() + " " + user.getLastName());
					values.put("seminar title", seminar.getSeminarName());
					values.put("seminar date",seminar.getSeminarStart().format(formatter));
					values.put("seminar id",String.valueOf(seminar.getSeminarID()));
					values.put("whatsapp link",seminar.getWhatsappLink());
						
					StrSubstitutor sub = new StrSubstitutor(values, "%(", ")");
					message.setBody(sub.replace(message.getBody()));
					message.setSubject(sub.replace(message.getSubject()));

					emailService.sendRegisterationMail(user, message);
					// whatsappService.sendRegisterationWhatsapp(user,message);
					rb.setStatus(200);
				} else {
					rb.setMessage("Registeration Unsuccessful");
					rb.setStatus(-1);
				}
			} else {
				rb.setMessage("You have Already Registered For this Seminar");
				rb.setStatus(-1);
			}
		}
		else {
			rb.setMessage("Registeration Unsuccessful");
			rb.setStatus(-1);
		}
		return rb;
	}

	@GetMapping("/getActiveSeminarList")
	public ResponseBeanWithList<SeminarBean> getActiveSemianrList() {
		ResponseBeanWithList<SeminarBean> rb = new ResponseBeanWithList<SeminarBean>();
		List<SeminarBean> data = seminarDao.getActiveSeminar();
		if (data != null) {
			rb.setMessage("Succesfully Fetched");
			rb.setData(data);
			rb.setStatus(200);
		} else {
			rb.setMessage("Some Error Occured");
			rb.setData(data);
			rb.setStatus(-1);
		}
		return rb;
	}
}
