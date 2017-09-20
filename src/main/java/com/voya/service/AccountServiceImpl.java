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

	public Account findAccountById(Integer id) {
		return accountDao.findAccountById(id);
	}

	public void saveOrUpdate(Account account) {
		if (findAccountById(account.getId()) == null) {
			accountDao.saveAccount(account);
		} else {
			accountDao.updateAccount(account);
		}
	}

	public List<Account> getAccountsByUser(User user) {
		return accountDao.getAllAccountsFromUser(user);
	}

	@Override
	public boolean isAccountNumberUnique(Integer id, String accountNumber) {
		Account account = accountDao.findByAccountNumber(accountNumber);
		return (account == null || ((id != null) && (account.getId() == id)));
	}
}
