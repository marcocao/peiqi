package com.cyy.peiqi.service;

import java.util.List;

import com.cyy.peiqi.domain.User;

public interface UserService {
	User findById(long id);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);
	
	List<User> findAllUsers();
	
	User findUserByEmail(String email);
	
	boolean isUserEmailUnique(long id, String email);
}