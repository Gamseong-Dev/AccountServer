package com.highluck.gamseong.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "USER")
@org.hibernate.annotations.DynamicUpdate
public class User {
	
	@Id
	private String id;
	@Column(name = "ACCOUNT")
	private String account;
	@Column
	private String password;
	@Column(nullable = true)
	private String name;
	@Column(name="IMAGE_URL", nullable = true)
	private String imageUrl;
	@Column(name="EMAIL_AUTH")
	private String emailAuth;
	
	@Transient
	@Column(name="ACCOUNT_DISTINCTION_CODE")
	private String accountDistinctionCode;
	
	@Transient
	@Column(name = "STATUS_CODE")
	private String statusCode;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Column(name = "CREATION_TIMESTAMP")
	private Timestamp creationTime;
	
	@Transient
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Column(name = "UPDATE_TIMESTAMP", nullable = true)
	private Timestamp updateTime;
	
	@Column(name = "TOKEN_KEY")
	private String tokenKey;
	
	@Column
	private String gender;
	
	@Column(name = "FACEBOOK_USER")
	private String facebookUser;
	
	public User(){}
	public User(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public String getEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(String emailAuth) {
		this.emailAuth = emailAuth;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getAccountDistinctionCode() {
		return accountDistinctionCode;
	}

	public void setAccountDistinctionCode(String accountDistinctionCode) {
		this.accountDistinctionCode = accountDistinctionCode;
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFacebookUser() {
		return facebookUser;
	}
	public void setFacebookUser(String facebookUser) {
		this.facebookUser = facebookUser;
	}
}
