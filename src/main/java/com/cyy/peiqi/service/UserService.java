package com.cyy.peiqi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cyy.peiqi.dao.UserDao;
import com.cyy.peiqi.domain.User;

@Service
public class UserService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveUser(User user)
	{
		logger.info("Save user {}", user);
		userDao.saveUser(user);
	}
	
	 @Transactional(propagation = Propagation.REQUIRED)
     public List<User> getUsers() {
         List<User> users = userDao.getUsers();
         logger.info("Get users {}", users);
         return users;
     }
}