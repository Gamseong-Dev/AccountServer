package com.highluck.gamseong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highluck.gamseong.model.entity.User;
import com.highluck.gamseong.model.response.CommonResponse;
import com.highluck.gamseong.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public CommonResponse save(@RequestBody User user) throws Throwable{
		
		return userService.save(user);
	}
}
