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

	public User findById(Integer id) {
		return userDao.fById(id);
	}

	public void saveOrUpdate(User user) {
		if (findById(user.getId()) == null) {
			userDao.save(user);
			User user2 = userDao.findbyemail(user.getEmail());
			Account account = new Account(user2);
			accountDao.save(account);
		} else {
			userDao.update(user);
		}
	}

	public List<User> getUsers() {
		return userDao.getAllUsers();
	}

	public boolean isUserEmailUnique(Integer id, String email) {
		User user = userDao.findbyemail(email);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

}