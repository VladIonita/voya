package com.voya.dao;

import java.util.List;

import com.voya.domain.Account;
import com.voya.domain.User;

public interface AccountDao {

	Account findAccountById(Integer id);

	boolean saveAccount(Account account);

	boolean updateAccount(Account account);

	List<Account> getAllAccountsFromUser(User user);

	Account findByAccountNumber(String accountNumber);
}
