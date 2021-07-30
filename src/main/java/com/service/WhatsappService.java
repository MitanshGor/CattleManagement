/*
 * package com.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.bean.SeminarRegistrationMailBean; import com.bean.UserBean; import
 * com.dao.SeminarRegistrationMailDao; import com.twilio.Twilio; import
 * com.twilio.converter.Promoter; import
 * com.twilio.rest.api.v2010.account.Message; import
 * com.twilio.type.PhoneNumber;
 * 
 * import java.net.URI; import java.math.BigDecimal;
 * 
 * @Service public class WhatsappService {
 * 
 * public static final String ACCOUNT_SID =
 * "AC044af9c27a791f424144e0ab9345375f"; public static final String AUTH_TOKEN =
 * "78cbe6172a0c45b54427fe646a26cf39";
 * 
 * @Autowired SeminarRegistrationMailDao seminarMailDao; public void
 * sendRegisterationWhatsapp(UserBean user,SeminarRegistrationMailBean message)
 * { Twilio.init(ACCOUNT_SID, AUTH_TOKEN); Message whatsappMessage =
 * Message.creator(
 * 
 * new com.twilio.type.PhoneNumber("whatsapp:+91"+user.getPhoneNumber()), new
 * com.twilio.type.PhoneNumber("whatsapp:+14155238886"),message.getBody())
 * .create(); } }
 */