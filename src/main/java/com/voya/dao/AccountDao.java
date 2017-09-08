package com.voya.dao;

import java.util.List;

import com.voya.domain.Account;
import com.voya.domain.User;

public interface AccountDao {

	Account fById(Integer id);

	boolean save(Account account);

	boolean update(Account account);

	List<Account> getAllAccounts(User user);
	
	Account findbyAccountNumber(String accountNumber);
	
}
