package com.voya.dao;

import java.util.List;

import com.voya.domain.User;

public interface UserDao {

	User findUserById(Integer id);

	boolean saveUser(User user);

	boolean updateUser(User user);

	boolean deleteUser(User user);

	List<User> getAllUsers();

	User findUserByEmail(String email);

	void deleteAllUsers();
}
