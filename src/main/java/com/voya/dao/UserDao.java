package com.voya.dao;

import java.util.List;

import com.voya.domain.User;

public interface UserDao {
	
	User fById(Integer id);
	
	boolean save(User user);
	
	boolean update(User user);
	
	boolean delete(User user);

	List<User> getAllUsers();

	User findbyemail(String email);
}
