package com.highluck.gamseong.model.response;

import org.springframework.stereotype.Component;

@Component
public class LogResponse {

	public final static String FAIL = "fail";
	public final static String SUCCESS = "success";
	public final static String HALF = "half";//메일인증 안된 계정
	public final static String NULL_MSG = "email or password is null";
	
	private String result;
	private String reason;
	private String tokenKey;
	
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
	public String getTokenKey() {
		return tokenKey;
	}
	public void setTokenKey(String token) {
		this.tokenKey = token;
	}
}
