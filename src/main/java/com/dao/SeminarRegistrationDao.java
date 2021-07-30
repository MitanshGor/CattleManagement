package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.SeminarRegistrationBean;

@Repository
public class SeminarRegistrationDao {

	@Autowired
	JdbcTemplate smt;
	
	public int registerSeminar(SeminarRegistrationBean registerBean) {
		int i = 0;
		try {
			smt.update("insert into seminarregistration(userid,seminarid,question) values(?,?,?)",registerBean.getUserID(),
					registerBean.getSeminarID(),registerBean.getQuestion());
			i = 1;
		}
		catch(Exception e) {
			i = -1;
		}
		return i;
	}

}
