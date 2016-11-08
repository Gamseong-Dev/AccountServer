package com.highluck.gamseong.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.highluck.gamseong.model.entity.User;
import com.highluck.gamseong.repository.interfaces.UserInterface;

@Repository
public class UserRepository {

	@Autowired
	private UserInterface userInterface;
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(User user){
		
		userInterface.save(user);
	}
	
	public User findByAccountAndFacebookUser(String account,String facebookUser){
		
		return userInterface.findByAccountAndFacebookUser(account , facebookUser);
	}
	
	public User findByAccount(String account){
		
		return userInterface.findByAccount(account);
	}
	
	public User findById(String id){
		
		return userInterface.findById(id);
	}
	
	public long count(){
		
		return userInterface.count();
	}
}
