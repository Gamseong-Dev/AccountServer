package com.highluck.gamseong.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "USER_LOG")
public class UserLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private String id;
	@Column
	private String account;
	@Column(name ="LOGIN_TIMESTAMP")
	private Timestamp loginTimeStamp;
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
	public Timestamp getLoginTimeStamp() {
		return loginTimeStamp;
	}
	public void setLoginTimeStamp(Timestamp loginTimeStamp) {
		this.loginTimeStamp = loginTimeStamp;
	}
}
