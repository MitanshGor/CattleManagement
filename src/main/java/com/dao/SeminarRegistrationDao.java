package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.SeminarRegisteredUsers;
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

	public List<SeminarRegisteredUsers> getAllRegisteredUsers(int seminarID){
		try {
			return smt.query("select * from usertable,semianrtable where seminar.userid=usertable.userid and seminar.seminarid = ?", new Object[] {seminarID},new int[] {java.sql.Types.BIGINT}, new BeanPropertyRowMapper<SeminarRegisteredUsers>(SeminarRegisteredUsers.class));
		}
		catch(Exception e) {
			return null;
		}
	}
}
