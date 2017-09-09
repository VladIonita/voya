package com.voya.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voya.controller.UserController;
import com.voya.dao.AccountDao;
import com.voya.dao.UserDao;
import com.voya.dao.UserDaoImpl;
import com.voya.domain.Account;
import com.voya.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountDao accountDao;

	public User findById(Integer id) {
		LOGGER.info("Get id " + id);
		return userDao.fById(id);
		
	}

	public void saveOrUpdate(User user) {
		if (findById(user.getId()) == null) {
			userDao.save(user);
			LOGGER.info("New user with account created");
			User user2 = userDao.findbyemail(user.getEmail());
			Account account = new Account(user2);
			if(accountDao.findbyAccountNumber(account.getAccount()) == null) {
				accountDao.save(account);
			}
		} else {
			LOGGER.info("Update User " + user.getEmail());
			userDao.update(user);
		}
	}

	public List<User> getUsers() {
		LOGGER.info("Get all Users");
		return userDao.getAllUsers();
	}

	public boolean isUserEmailUnique(Integer id, String email) {
		User user = userDao.findbyemail(email);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public void deleteUser(User user) {
		LOGGER.info("Deleted user " + user.getEmail());
		userDao.delete(user);
	}
	
	

}
