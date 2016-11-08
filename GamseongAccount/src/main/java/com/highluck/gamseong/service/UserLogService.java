package com.highluck.gamseong.service;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.highluck.gamseong.common.LibraryContainer;
import com.highluck.gamseong.model.entity.User;
import com.highluck.gamseong.model.entity.UserLog;
import com.highluck.gamseong.model.response.LogResponse;
import com.highluck.gamseong.model.value.UserValue;
import com.highluck.gamseong.repository.UserLogRepository;
import com.highluck.gamseong.repository.UserRepository;

@Service
public class UserLogService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserLogRepository userLogRepository;
	@Autowired
	private LibraryContainer library;
	@Autowired
	private LogResponse logResponse;
	
	@Transactional(readOnly = false)
	public LogResponse login(UserValue value) throws Throwable{
		
		User user = userRepository.findByAccountAndFacebookUser(value.getAccount(),"N");
		UserLog log = new UserLog();
		
		if(user.getPassword().equals(library.getEncryption().getEncSHA256(value.getPassword()))){
			log.setAccount(value.getAccount());
			log.setLoginTimeStamp(Timestamp.valueOf(LocalDateTime.now()));			
			
			logResponse.setTokenKey(library.getEncryption().getEncSHA256(value.getAccount() + library.getAuthCodeCreator().SecurityCode()));			
			user.setTokenKey(logResponse.getTokenKey());

			logResponse.setResult(user.getEmailAuth().equals("Y") ? logResponse.SUCCESS: logResponse.HALF);
			logResponse.setUser(user);
			userLogRepository.save(log);		
		}
		else{	
			logResponse.setResult(logResponse.FAIL);
			logResponse.setReason("아이디 또는 비밀번호가 틀립니다.");	
		}
		return logResponse;
	}
	
	@Transactional
	public LogResponse logout(UserValue value){
		
		User user = userRepository.findByAccount(value.getAccount());
		
		if(user.getTokenKey().equals(value.getTokenKey())){
			user.setTokenKey("GG");
			logResponse.setResult(logResponse.SUCCESS);
			logResponse.setReason("로그아웃");
			logResponse.setTokenKey("GG");
		}
		else{
			logResponse.setResult(logResponse.FAIL);
			logResponse.setReason("잘못된 정보");
			
		}
		return logResponse;
	}
	
	@Transactional
	public LogResponse facebookLogin(User value) throws Exception{
		User user;
		UserLog log = new UserLog();
		
		value.setTokenKey(library.getEncryption().getEncSHA256(value.getAccount() 
				+ library.getAuthCodeCreator().SecurityCode()));
		try{
			
			user = userRepository.findById(value.getId());
			user.setTokenKey(value.getTokenKey());
			logResponse.setUser(user);
			
		}catch(NullPointerException e){
			
			if(value.getGender().equals("male")) value.setGender("M");
			else if(value.getGender().equals("female")) value.setGender("F");
			
			value.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
			value.setStatusCode("Y");
			
			value.setEmailAuth("N");	
			value.setFacebookUser("Y");
			value.setPassword("facebook");
			
			userRepository.save(value);
			
			logResponse.setUser(value);	
		}
		
		logResponse.setTokenKey(value.getTokenKey());
		logResponse.setResult(logResponse.SUCCESS);
		
		log.setAccount(value.getAccount());
		log.setLoginTimeStamp(Timestamp.valueOf(LocalDateTime.now()));	
		userLogRepository.save(log);
		
		return logResponse;
	}
	
}
