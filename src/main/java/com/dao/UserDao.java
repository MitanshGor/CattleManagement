package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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

	public long insertUser(final UserBean userBean) {
		long userID = 0;

		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		try {
			LocalDateTime ct = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
			Timestamp ctim = Timestamp.valueOf(ct);  
			smt.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement psmt = con.prepareStatement("insert into usertable(emailid,password,"
							+ "firstname,lastname,phonenumber,createdat) values(?,?,?,?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
					psmt.setString(1, userBean.getEmailID());
					psmt.setString(2, userBean.getPassword());
					String firstName = userBean.getFirstName().substring(0,1).toUpperCase();
					String remainFirstName = userBean.getFirstName().substring(1).toLowerCase();
					
					psmt.setString(3, firstName+remainFirstName);
					String lastName = userBean.getLastName().substring(0,1).toUpperCase();
					String remainLastName = userBean.getLastName().substring(1).toLowerCase();
					
					psmt.setString(4, lastName+remainLastName);
					psmt.setString(5, userBean.getPhoneNumber());
					psmt.setTimestamp(6, ctim);
					return psmt;
				}
			},holder);	
			userID = (Long)holder.getKeys().get("userid");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return userID;
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

	public int insertUserProfile(int userID) {
		int i = 0;
		try {
			smt.update("insert into userprofiletable(userid) values(?)",userID);
			i = 1;
		}catch(Exception e) {
			i= -1;
		}
		return i;
	}
}
