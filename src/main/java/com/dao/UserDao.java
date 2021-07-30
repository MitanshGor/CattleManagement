package com.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.LoginBean;
import com.bean.UserBean;

@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate smt;
	
	public UserBean getUserByLogin(LoginBean loginBean) {
		try {
			return smt.queryForObject("select * from usertable where emailid = ? and password = ?", new Object[] {loginBean.getEmailID(),loginBean.getPassword()},new int[] {java.sql.Types.LONGVARCHAR,java.sql.Types.LONGVARCHAR},new BeanPropertyRowMapper<UserBean>(UserBean.class));
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid login");
		}
		return null;
	}
	
	public boolean checkDuplicateEmail(String email) {
		List<UserBean> users = smt.query("select * from usertable where emailid = ?",new Object[] {email}, new int[] {java.sql.Types.LONGVARCHAR}, new BeanPropertyRowMapper<UserBean>(UserBean.class));
		if (users.size() != 0)
			return false;
		else
			return true;
	}

	public void insertUser(UserBean userBean) {
		try {
			smt.update("insert into usertable(emailid,password,firstname,lastname,phonenumber) values(?,?,?,?,?)",
								userBean.getEmailID(),userBean.getPassword(),userBean.getFirstName(),
								userBean.getLastName(),userBean.getPhoneNumber());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public UserBean getUserByID(int userID) {
		List<UserBean> users = smt.query("select * from usertable where userid = ?",new Object[] {userID}, new int[] {java.sql.Types.BIGINT}, new BeanPropertyRowMapper<UserBean>(UserBean.class));
		if (users.size() != 0)
			return users.get(0);
		else
			return null;
	}
	public UserBean getUserByEmailID(String emailID) {
		List<UserBean> users = smt.query("select * from usertable where emailid = ?",new Object[] {emailID}, new int[] {java.sql.Types.LONGVARCHAR}, new BeanPropertyRowMapper<UserBean>(UserBean.class));
		if (users.size() != 0)
			return users.get(0);
		else
			return null;
	}
}
