package com.voya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voya.dao.AccountDao;
import com.voya.dao.UserDao;
import com.voya.domain.Account;
import com.voya.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccountDao accountDao;

	public User findUserById(Integer id) {
		return userDao.findUserById(id);

	}

	public void saveUser(User user) {
		userDao.saveUser(user);
		User currentUser = userDao.findUserByEmail(user.getEmail());
		Account account = new Account(currentUser);
		if (accountDao.findByAccountNumber(account.getAccount()) == null) {
			accountDao.saveAccount(account);
		}
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public boolean isUserEmailUnique(Integer id, String email) {
		User user = userDao.findUserByEmail(email);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	public boolean isUserExist(User user) {
		if (userDao.findUserByEmail(user.getEmail()) == null) {
			return false;
		} else {
			return true;
		}
	}

	public void deleteAllUsers() {
		userDao.deleteAllUsers();
	}

}
