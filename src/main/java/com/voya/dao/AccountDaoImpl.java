package com.voya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.voya.domain.Account;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

//	public Account fById(Integer id) {
//
//		try {
//			String sqlQuery = "SELECT * FROM account WHERE id =?";
//			Object[] args = new Object[] { id };
//			Account account = jdbcTemplate.queryForObject(sqlQuery, args, new RowMapper<Account>() {
//				public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
//					account.setId(rs.getInt("id"));
//					account.setAccount(rs.getString("account"));
//					account.setBalance(rs.getInt("balance"));
//					return account;
//				}
//			});
//			return account;
//		} catch (EmptyResultDataAccessException e) {
//			return null;
//		}
//	}

	public boolean save(Account account) {
		String sqlQuery = "INSERT INTO account (account, user_id) VALUES (?, ?)";
		Object[] args = new Object[] { account.getAccount(),account.getUser().getId() };
		return jdbcTemplate.update(sqlQuery, args) == 1;
	}

	public boolean update(Account account) {
		String sqlQuery = "UPDATE account SET balance = ? WHERE id = ?";
		Object[] args = new Object[] { account.getBalance(), account.getId() };
		return jdbcTemplate.update(sqlQuery, args) == 1;
	}

//	public List<Account> getAllAccounts() {
//		return jdbcTemplate.query("SELECT * FROM account", new RowMapper<Account>() {
//			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Account account = new Account();
//				account.setId(rs.getInt("id"));
//				account.setAccount(rs.getString("account"));
//				account.setBalance(rs.getInt("balance"));
//				return account;
//			}
//		});
//	}

}
