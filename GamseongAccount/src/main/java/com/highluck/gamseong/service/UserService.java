package com.highluck.gamseong.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.highluck.gamseong.common.LibraryContainer;
import com.highluck.gamseong.common.library.MailMessage;
import com.highluck.gamseong.model.entity.User;
import com.highluck.gamseong.model.response.CommonResponse;
import com.highluck.gamseong.repository.UserRepository;

@Service
public class UserService {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public final static String MAIL_AUTH = "인증메일 입니다.";
	
	@Autowired
	private LibraryContainer library;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommonResponse commonResponse;
	
	@Transactional(readOnly = false)
	public CommonResponse save(User user) throws Throwable{
		
		if(user.getAccount() == null || user.getPassword() == null) {
			commonResponse.setResult(CommonResponse.FAIL);
			commonResponse.setReason(CommonResponse.FAIL);
		}
		else if(userRepository.findByAccount(user.getAccount()) !=null){
			commonResponse.setReason(commonResponse.MAIL_OVER);
			commonResponse.setResult(commonResponse.OVER);
		}
		
		else{
			user.setId("0"+String.valueOf(userRepository.count()+1)+library.getAuthCodeCreator().RandomCode());	
			user.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
			user.setStatusCode("Y");
			user.setPassword(library.getEncryption().getEncSHA256(user.getPassword()));
			user.setTokenKey(library.getEncryption().getEncSHA256(user.getAccount() 
					+ library.getAuthCodeCreator().SecurityCode()));
			user.setEmailAuth("N");
			
			MailMessage mail = new MailMessage();
			mail.setReciver(user.getAccount());
			mail.setSubject(MAIL_AUTH);
			mail.setTokenKey(user.getTokenKey());
			
			library.getEmailSender().sendAuthMail(mail);
			userRepository.save(user);
		
			commonResponse.setResult(commonResponse.SUCCESS);
		}
		return commonResponse;
	}

}
