package com.voya.service;

import java.util.List;

import com.voya.domain.Account;
import com.voya.domain.User;

public interface AccountService {

	Account findAccountById(Integer id);

	void saveOrUpdate(Account account);

	public List<Account> getAccountsByUser(User user);

	boolean isAccountNumberUnique(Integer id, String account);

}
