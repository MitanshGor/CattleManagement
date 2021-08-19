package com.controller;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.bean.UserProfileBean;
import com.dao.UserDao;
import com.service.GoogleDriveService;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	GoogleDriveService googleDriveService;
	
	@PostMapping("/loginUser")
	public ResponseBean<UserBean> loginUser(LoginBean loginBean){
		ResponseBean<UserBean> rb = new ResponseBean<UserBean>();
		UserBean user = userDao.getUserByLogin(loginBean);
		
		if(user != null) {
			rb.setData(user);
			rb.setMessage("Succesfully Login");
			rb.setStatus(200);
		}
		else {
			rb.setData(user);
			rb.setMessage("Invalid Credentials");
			rb.setStatus(-1);
		}
		return rb;
	}
	
	@PostMapping("/signUpUser")
	public ResponseBean<UserBean> signUpUser(UserBean userBean){
		ResponseBean<UserBean> rb = new ResponseBean<UserBean>(); 
		rb.setData(userBean);
		if(userDao.checkDuplicateEmail(userBean.getEmailID().trim())){
			int userID = (int)userDao.insertUser(userBean);
			int i = 0;
			if(userID !=0) {
				userBean.setUserID(userID);
				i = userDao.insertUserProfile(userID);
			}
			if(i==1) {
				rb.setStatus(200);
				rb.setMessage("Sign up Succesfull");				
			}
			else {
				rb.setStatus(-1);
				rb.setMessage("Some error occured");
			}
		}
		else {
			rb.setStatus(-1);
			rb.setMessage("Email ID Already Registered");
		}
		return rb;
	}
	@PostMapping("/signUpUserProfile")
	public ResponseBean<UserProfileBean> signUpUserProfile(UserProfileBean userProfileBean,@RequestParam("image") MultipartFile file){
		ResponseBean<UserProfileBean> rb = new ResponseBean<UserProfileBean>(); 
		System.out.println("FIle name ->" +file.getOriginalFilename());
		System.out.println("User EmailID ->" +userProfileBean.getEmailID());
		System.out.println("Image Description ->" +userProfileBean.getImageDesc());
		if(!file.isEmpty()) {
			googleDriveService.upLoadFile(file, "profile");
		}
		rb.setData(userProfileBean);
		rb.setStatus(200);
		rb.setMessage("Profile Setup Succesfull");
		
		
		return rb;
	}
}
