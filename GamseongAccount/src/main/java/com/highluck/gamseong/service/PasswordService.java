package com.highluck.gamseong.service;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.highluck.gamseong.common.LibraryContainer;
import com.highluck.gamseong.common.library.MailMessage;
import com.highluck.gamseong.model.entity.User;
import com.highluck.gamseong.model.response.CommonResponse;
import com.highluck.gamseong.model.value.UserValue;
import com.highluck.gamseong.repository.interfaces.UserInterface;

@Service
public class PasswordService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final static String MAIL_PASSWORD = "패스워드 변경 메일 입니다.";
	private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$";

	@Autowired
	private UserInterface userInterface;
	@Autowired
	private CommonResponse commonResponse;
	@Autowired
	private LibraryContainer library;
	
	@Transactional
	public CommonResponse mailPassword(UserValue value) throws Throwable{
					
			User user = userInterface.findByAccount(value.getAccount());
			user.setTokenKey(library.getEncryption().getEncSHA256(user.getAccount() + library.getAuthCodeCreator().SecurityCode()));
			
			MailMessage mail = new MailMessage();
			mail.setReciver(value.getAccount());
			mail.setTokenKey(user.getTokenKey());
			mail.setSubject(MAIL_PASSWORD);
			
			library.getEmailSender().sendPasswordMail(mail);
			
		
			commonResponse.setResult(commonResponse.SUCCESS);
		
		return commonResponse;
	}
	
	@Transactional
	public boolean changePassword(UserValue value) throws Exception{
		
		User user = userInterface.findByAccount(value.getAccount());
		
		if(user.getTokenKey().equals(value.getTokenKey())){
			user.setPassword(library.getEncryption().getEncSHA256(value.getPassword()));
			user.setTokenKey("");
			return true;
		}
		else return false;
	}
	
	
	public boolean passwordRegular(String password){
		
		return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
	}
}
