package com.cyy.peiqi.dao;

import java.util.List;

import com.cyy.peiqi.domain.User;

public interface UserDao {
	User findUserById(long id);

	void saveUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();
	
	User findUserByEmail(String email);
}