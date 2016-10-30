package com.highluck.gamseong.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.highluck.gamseong.model.entity.User;

@Component
public interface UserInterface extends CrudRepository<User, String>{

	 User findByAccount(String account);
	 
	 User save(User user);
}
