package com.highluck.gamseong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.highluck.gamseong.common.LibraryContainer;
import com.highluck.gamseong.common.library.MailMessage;
import com.highluck.gamseong.model.entity.User;
import com.highluck.gamseong.model.response.CommonResponse;
import com.highluck.gamseong.model.value.UserValue;
import com.highluck.gamseong.repository.UserRepository;
import com.highluck.gamseong.repository.interfaces.UserInterface;

@Service
public class AuthService {

	@Autowired
	private UserInterface userInterface;
	@Autowired
	private CommonResponse commonResponse;
	@Autowired
	private LibraryContainer library;
	
	@Transactional(readOnly=false)
	public boolean mailAuth(UserValue value){
		System.out.println(value.getAccount()+"-----------");
		User user = userInterface.findByAccount(value.getAccount());
		
		if(user.getTokenKey().equals(value.getTokenKey())){
			user.setEmailAuth("Y");
			return true;
		}
		else return false;
	}
	
	@Transactional
	public CommonResponse mailAuthSend(UserValue value) throws Exception{
		
		User user = userInterface.findByAccount(value.getAccount());
		
		if(user.getEmailAuth().equals("N")){
			user.setTokenKey(library.getEncryption().getEncSHA256(user.getAccount() + library.getAuthCodeCreator().SecurityCode()));
			
			MailMessage mail = new MailMessage();
			mail.setReciver(user.getAccount());
			mail.setTokenKey(user.getTokenKey());
			mail.setSubject(UserService.MAIL_AUTH);
			
			library.getEmailSender().sendAuthMail(mail);
			commonResponse.setResult(commonResponse.SUCCESS);
		}
		else{
			commonResponse.setResult(commonResponse.FAIL);
			commonResponse.setReason(commonResponse.MAIL_AUTH_ERROR);
		}
		
		return commonResponse;
	}
}
