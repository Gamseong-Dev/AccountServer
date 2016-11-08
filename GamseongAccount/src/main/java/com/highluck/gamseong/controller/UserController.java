package com.highluck.gamseong.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highluck.gamseong.model.entity.User;
import com.highluck.gamseong.model.response.CommonResponse;
import com.highluck.gamseong.model.value.UserPostValue;
import com.highluck.gamseong.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Callable<CommonResponse> save(@RequestBody User user){
		
		return () -> {
			try {
				return userService.save(user);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				CommonResponse response = new CommonResponse();
				response.setResult("fail");
				response.setReason(e.toString());
				return null;
			}
		};
	}
	
	@RequestMapping(value = "/{user.id}", method = RequestMethod.PUT)
	public CommonResponse set(@RequestBody UserPostValue value) throws Throwable{
		
		return userService.set(value);
	}
}
