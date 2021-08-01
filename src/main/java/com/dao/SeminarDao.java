package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.SeminarBean;

@Repository
public class SeminarDao {

	@Autowired
	JdbcTemplate smt;
	public int  addSeminar(SeminarBean seminarBean) {
		int i = 0;
		try {
			smt.update("insert into seminartable(seminarname,seminartype,seminarfees,seminarstart,seminarend,"
					+ "seminarregistrationstart,seminarregistrationend,acceptingregistration,seminarzoomlink,seminardescription,"
					+ "imgpathGujarati,imgpathenglish,whatsapplink) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",seminarBean.getSeminarName(),
					seminarBean.getSeminarType(),seminarBean.getSeminarFees(),seminarBean.getSeminarStart(),
					seminarBean.getSeminarEnd(),seminarBean.getSeminarRegistrationStart(),seminarBean.getSeminarRegistrationEnd(),
					seminarBean.isAcceptingRegistration(),seminarBean.getSeminarZoomLink(),seminarBean.getSeminarDescription(),
					seminarBean.getImgPathGujarati(),seminarBean.getImgPathEnglish(),seminarBean.getWhatsappLink());
			i = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
			i = -1;
		}
		return i;
	}
	public SeminarBean getSeminarByID(int seminarID) {
		try {
			return smt.query("select * from seminartable where seminarid=?",new Object[] {seminarID},new int[] {java.sql.Types.BIGINT}, new BeanPropertyRowMapper<SeminarBean>(SeminarBean.class)).get(0);
		}catch(Exception e) {
			return null;
		}
	}
	public List<SeminarBean> getAllSeminar() {
		try {
			return smt.query("select * from seminartable", new BeanPropertyRowMapper<SeminarBean>(SeminarBean.class));
		}catch(Exception e) {
			return null;
		}
	}
	public int updateSeminar(SeminarBean seminarBean) {
		int i = 0;
		try {
			smt.update("update seminartable set seminarname = ?,seminartype = ? ,seminarfees = ? ,seminarstart = ?,seminarend= ?,"
					+ "seminarregistrationstart= ?,seminarregistrationend= ?,acceptingregistration= ?,seminarzoomlink= ?,seminardescription= ?"
					+ ",imgpathGujarati= ?,imgpathenglish= ?,whatsapplink= ? where seminarid= ?",seminarBean.getSeminarName(),
					seminarBean.getSeminarType(),seminarBean.getSeminarFees(),seminarBean.getSeminarStart(),
					seminarBean.getSeminarEnd(),seminarBean.getSeminarRegistrationStart(),seminarBean.getSeminarRegistrationEnd(),
					seminarBean.isAcceptingRegistration(),seminarBean.getSeminarZoomLink(),seminarBean.getSeminarDescription(),
					seminarBean.getImgPathGujarati(),seminarBean.getImgPathEnglish(),seminarBean.getWhatsappLink(),seminarBean.getSeminarID());
			i  = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
			i = -1;
		}
		return i;
	}

	public List<SeminarBean> getActiveSeminar(){
		try {
			return smt.query("select * from seminartable where (current_timestamp between seminarregistrationstart::timestamp and seminarregistrationend::timestamp) and (acceptingregistration=true)", new BeanPropertyRowMapper<SeminarBean>(SeminarBean.class));
		}
		catch(Exception e) {
			return null;
		}
	}
}
