package com.highluck.gamseong.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.highluck.gamseong.model.entity.UserLog;

@Component
public interface UserLogInterface extends CrudRepository<UserLog, Long>{

	UserLog save(UserLog log);
}
