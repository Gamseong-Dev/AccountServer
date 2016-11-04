package com.highluck.gamseong.model.value;

import org.springframework.web.multipart.MultipartFile;

import com.highluck.gamseong.model.entity.User;

public class UserPostValue {

	private MultipartFile sourceFile;
	private User user;
	private String path;
	
	public MultipartFile getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(MultipartFile sourceFile) {
		this.sourceFile = sourceFile;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
