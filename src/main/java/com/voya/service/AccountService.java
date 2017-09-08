package com.voya.service;

import java.util.List;

import com.voya.domain.Account;
import com.voya.domain.User;

public interface AccountService {
	
	Account findById(Integer id);
	
	void saveOrUpdate(Account account);
	
	public List<Account> getAccount(User user);

	boolean isAccountNumberUnique(Integer id, String account);
	
}
