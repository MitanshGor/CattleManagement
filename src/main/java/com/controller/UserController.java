package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDao;
	
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
			userDao.insertUser(userBean);
			rb.setStatus(200);
			rb.setMessage("Sign up Succesfull");
		}
		else {
			rb.setStatus(-1);
			rb.setMessage("Email ID Already Registered");
		}
		return rb;
	}
	
}
