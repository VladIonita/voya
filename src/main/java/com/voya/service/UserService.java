package com.voya.service;

import java.util.List;

import com.voya.domain.User;

public interface UserService {

	User findById(Integer id);

	void save(User user);

	void update(User user);

	public List<User> getUsers();

	boolean isUserEmailUnique(Integer id, String email);

	void deleteUser(User user);

}
