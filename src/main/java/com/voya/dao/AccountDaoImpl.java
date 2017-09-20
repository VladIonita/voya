package com.voya.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.voya.domain.Account;
import com.voya.domain.User;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

	@Autowired
	UserDao userDao;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Account findAccountById(Integer id) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM account WHERE id =?", new Object[] { id },
					(resultSet, rowNum) -> {
						return new Account(resultSet.getInt("id"), userDao.findUserById(resultSet.getInt("user_id")),
								resultSet.getString("account"), resultSet.getInt("balance"));
					});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean saveAccount(Account account) {
		return jdbcTemplate.update("INSERT INTO account (account, user_id) VALUES (?, ?)",
				new Object[] { account.getAccount(), account.getUser().getId() }) == 1;
	}

	public boolean updateAccount(Account account) {
		return jdbcTemplate.update("UPDATE account SET balance = ? WHERE id = ?",
				new Object[] { account.getBalance(), account.getId() }) == 1;
	}

	public List<Account> getAllAccountsFromUser(User user) {
		try {
			return jdbcTemplate.query("SELECT * FROM account WHERE user_id = ?", new Object[] { user.getId() },
					(resultSet, rowNum) -> {
						return new Account(resultSet.getInt("id"), userDao.findUserById(resultSet.getInt("user_id")),
								resultSet.getString("account"), resultSet.getInt("balance"));
					});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Account findByAccountNumber(String accountNumber) {
		try {
			Account account = jdbcTemplate.queryForObject("SELECT * FROM account WHERE account = ?",
					new Object[] { accountNumber }, (resultSet, rowNum) -> {
						return new Account(resultSet.getInt("id"), userDao.findUserById(resultSet.getInt("user_id")),
								resultSet.getString("account"), resultSet.getInt("balance"));
					});
			return account;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
