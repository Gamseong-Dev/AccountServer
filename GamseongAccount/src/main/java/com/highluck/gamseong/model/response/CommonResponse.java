package com.highluck.gamseong.model.response;

import org.springframework.stereotype.Component;

@Component
public class CommonResponse {

	public final static String FAIL = "fail";
	public final static String SUCCESS = "success";
	public final static String HALF = "half";
	public final static String OVER = "over";
	
	public final static String NULL_MSG = "email or password is null";
	public final static String MAIL_OVER = "중복되는 메일이 있습니다.";
	public final static String TOKEN_ERROR = "token error";
	public final static String PASSWORD_ERROR = "패스워드 형식이 맞지 않습니다.";
	public final static String MAIL_AUTH_ERROR = "이미 인증한 메일입니다.";
		
	private String result;
	private String reason;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
