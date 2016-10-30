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
		
		User user = userRepository.findByAccount(value.getAccount());
		UserLog log = new UserLog();
		
		if(user.getPassword().equals(library.getEncryption().getEncSHA256(value.getPassword()))){
			logResponse.setResult(user.getEmailAuth().equals("Y") ? logResponse.SUCCESS: logResponse.HALF);
			logResponse.setTokenKey(library.getEncryption().getEncSHA256(value.getAccount() + library.getAuthCodeCreator().SecurityCode()));
			log.setAccount(value.getAccount());
			log.setLoginTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
		
			user.setTokenKey(logResponse.getTokenKey());
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
			logResponse.setTokenKey(user.getTokenKey());
		}
		else{
			logResponse.setResult(logResponse.FAIL);
			logResponse.setReason("잘못된 정보");
			
		}
		return logResponse;
	}
	
}
