package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.SeminarRegistrationMailBean;

@Repository
public class SeminarRegistrationMailDao {
	
	@Autowired
	JdbcTemplate smt;
	
	public SeminarRegistrationMailBean getMessage() {
		return smt.query("select * from seminarregistrationmessage", new BeanPropertyRowMapper<SeminarRegistrationMailBean>(SeminarRegistrationMailBean.class)).get(0);
	}
}
