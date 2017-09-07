package com.voya.dao;

import java.util.List;

import com.voya.domain.Account;

public interface AccountDao {

//	Account fById(Integer id);

	boolean save(Account account);

	boolean update(Account account);

//	List<Account> getAllAccounts();

}
