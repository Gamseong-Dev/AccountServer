package com.highluck.gamseong.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.highluck.gamseong.model.entity.UserLog;

@Repository
public class UserLogRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(UserLog log){
		
		entityManager.persist(log);
	}
}
