package com.highluck.gamseong.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.highluck.gamseong.common.library.AuthCodeCreator;
import com.highluck.gamseong.common.library.EmailSender;
import com.highluck.gamseong.common.library.Encryption;
import com.highluck.gamseong.common.library.FileUpload;

@Component("library")
public class LibraryContainer {

	@Autowired
	private AuthCodeCreator authCodeCreator;
	@Autowired
	private Encryption encryption;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private FileUpload fileUpload;
	
	public AuthCodeCreator getAuthCodeCreator() {
		return authCodeCreator;
	}
	public void setAuthCodeCreator(AuthCodeCreator authCodeCreator) {
		this.authCodeCreator = authCodeCreator;
	}
	public Encryption getEncryption() {
		return encryption;
	}
	public void setEncryption(Encryption encryption) {
		this.encryption = encryption;
	}
	public EmailSender getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}
	public FileUpload getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}
	
}
