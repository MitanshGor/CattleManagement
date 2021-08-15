package com.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bean.PersonalCounsellingBookBean;
import com.bean.ResponseBean;
import com.bean.ResponseBeanWithList;
import com.bean.TimeSlotBean;
import com.bean.UserBean;
import com.dao.TimeSlotDao;
import com.dao.UserDao;

@RestController
public class PersonalCounsellingController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	TimeSlotDao timeSlotDao;
	@GetMapping("getAllActiveCounsellingSlots")
	public ResponseBeanWithList<TimeSlotBean> getAllActiveCounsellingSlots(){
		ResponseBeanWithList<TimeSlotBean> rb = new ResponseBeanWithList<TimeSlotBean>();
		rb.setMessage("Fetch Successfully");
		rb.setStatus(400);
		rb.setData(timeSlotDao.getAllActiveCounsellingSlots());
		return rb;
	}
	@GetMapping("requestForPersonalCounselling/{emailID}/{timeSlotID}")
	public ResponseBean<PersonalCounsellingBookBean> requestForPersonalCounselling(@PathVariable("emailID") String emailID,@PathVariable("timeSlotID") int timeSlotID){
		ResponseBean<PersonalCounsellingBookBean> rb = new ResponseBean<PersonalCounsellingBookBean>();
		UserBean user = userDao.getUserByEmailID(emailID);
		TimeSlotBean timeSlotBean = timeSlotDao.getTimeSlotByID(timeSlotID);
		PersonalCounsellingBookBean pb = new PersonalCounsellingBookBean();
		pb.setTimeSlotID(timeSlotBean.getTimeSlotID());
		pb.setUserID(user.getUserID());
		LocalDateTime ct = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
		pb.setRequestedAt(Timestamp.valueOf(ct));  
		if(timeSlotBean != null && !timeSlotBean.isBooked() && !timeSlotDao.checkWhetherUserAlreadyRegistered(user.getUserID(), timeSlotID)) {
			if(timeSlotDao.insertRequestPersonalCounselling(pb)) {
				rb.setMessage("Successfull Applied For Request");
				rb.setStatus(200);				
			}
			else {
				rb.setMessage("Some error occured");
				rb.setStatus(-1);
			}
		}else {
			rb.setMessage("Already Registered Slot");
			rb.setStatus(-1);
		}
		rb.setData(pb);
		return rb;
	}
}
