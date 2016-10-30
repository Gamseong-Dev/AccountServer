package com.highluck.gamseong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highluck.gamseong.model.response.CommonResponse;
import com.highluck.gamseong.model.response.LogResponse;
import com.highluck.gamseong.model.value.UserValue;
import com.highluck.gamseong.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "/mail", method = RequestMethod.POST)
	public String mailAuth(@RequestBody UserValue value) throws Throwable{
		
		if(authService.mailAuth(value)) return "인증되었습니다.";
		else return "인증 실패 하였습니다.";
	}
	
	@RequestMapping(value = "/mail/send", method = RequestMethod.POST)
	public CommonResponse mailAuthSend(@RequestBody UserValue value) throws Throwable{
		
		return authService.mailAuthSend(value);
	}
}
