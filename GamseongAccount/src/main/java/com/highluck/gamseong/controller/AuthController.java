package com.highluck.gamseong.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public Callable<String> mailAuth(@ModelAttribute UserValue value) throws Throwable{
		
		return () ->{
			return authService.mailAuth(value)?  "Auth Success" : "error";
		};
	}
	
	@RequestMapping(value = "/mail/send", method = RequestMethod.POST)
	public CommonResponse mailAuthSend(@RequestBody UserValue value) throws Throwable{
		
		return authService.mailAuthSend(value);
	}
}
