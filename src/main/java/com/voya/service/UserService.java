package com.voya.service;

import java.util.List;

import com.voya.domain.User;

public interface UserService {

	User findUserById(Integer id);

	void saveUser(User user);

	void updateUser(User user);

	public List<User> getAllUsers();

	boolean isUserEmailUnique(Integer id, String email);

	void deleteUser(User user);

	boolean isUserExist(User user);

	void deleteAllUsers();

}
