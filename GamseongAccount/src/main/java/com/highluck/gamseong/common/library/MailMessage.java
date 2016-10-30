package com.highluck.gamseong.common.library;

import javax.mail.Multipart;

public class MailMessage {

	private String sender;
	private String reciver;
	private String message;
	private String subject;
	private String tokenKey;
	private Multipart file;
	
	public Multipart getFile() {
		return file;
	}
	public void setFile(Multipart file) {
		this.file = file;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciver() {
		return reciver;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTokenKey() {
		return tokenKey;
	}
	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}	
}
