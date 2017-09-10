package com.voya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voya.dao.AccountDao;
import com.voya.domain.Account;
import com.voya.domain.User;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	public Account findById(Integer id) {
		return accountDao.fById(id);
	}

	public void saveOrUpdate(Account account) {
		if (findById(account.getId()) == null) {
			accountDao.save(account);
		} else {
			accountDao.update(account);
		}
	}

	public List<Account> getAccount(User user) {
		return accountDao.getAllAccounts(user);
	}

	@Override
	public boolean isAccountNumberUnique(Integer id, String accountNumber) {
		Account account = accountDao.findbyAccountNumber(accountNumber);
		return (account == null || ((id != null) && (account.getId() == id)));
	}
}
