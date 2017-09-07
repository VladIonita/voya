package com.voya.service;

import java.util.List;

import com.voya.domain.User;

public interface UserService {
	
	User findById(Integer id);
	
	void saveOrUpdate(User user);
	
	public List<User> getUsers();

	boolean isUserEmailUnique(Integer id, String email);

}
