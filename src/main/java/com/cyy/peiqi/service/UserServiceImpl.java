package com.cyy.peiqi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cyy.peiqi.dao.UserDao;
import com.cyy.peiqi.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
	/*
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
	 
	 @Transactional(propagation = Propagation.REQUIRED)
	 public User getUser(String id)
	 {
		 return userDao.getUser(id);
	 }
	 
	 @Transactional(propagation=Propagation.REQUIRED)
	 public void deleteUserById(String id) {
		userDao.deleteUserById(id);
	}
*/
	@Transactional(propagation=Propagation.REQUIRED)
	public User findById(long id) {
		return userDao.findUserById(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void saveUser(User user)
	{
		logger.info("Save user {}", user);
		userDao.saveUser(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUser(User user) {
		User oldUser = findById(user.getId());
		
		if(oldUser != null)
		{
			oldUser.setFirstName(user.getFirstName());
			oldUser.setEmailId(user.getEmailId());
			oldUser.setLastName(user.getLastName());
			oldUser.setPassword(user.getPassword());
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUserById(long id) {
		userDao.deleteUserById(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public boolean isUserEmailUnique(long id, String email) {
		User user = findUserByEmail(email);
		
		return (user == null || id == user.getId());
	}
}