package com.highluck.gamseong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highluck.gamseong.model.entity.User;
import com.highluck.gamseong.model.response.CommonResponse;
import com.highluck.gamseong.model.response.LogResponse;
import com.highluck.gamseong.model.value.UserValue;
import com.highluck.gamseong.service.UserLogService;
import com.highluck.gamseong.service.UserService;

@RestController
@RequestMapping("/users")
public class LogController {

	@Autowired
	private UserLogService userLogService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LogResponse login(@RequestBody UserValue value) throws Throwable{
		
		return userLogService.login(value);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public LogResponse logout(@RequestBody UserValue value) throws Throwable{
		
		return userLogService.logout(value);
	}
}
