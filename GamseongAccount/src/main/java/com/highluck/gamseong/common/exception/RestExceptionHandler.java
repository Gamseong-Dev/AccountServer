package com.highluck.gamseong.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.highluck.gamseong.model.response.CommonResponse;

@EnableWebMvc
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Throwable.class)
    public CommonResponse handleControllerException(HttpServletRequest req, Throwable ex) {
		
		CommonResponse response = new CommonResponse();
		response.setResult("fail");
		response.setReason(ex.toString());
		return response; 
    }
}
