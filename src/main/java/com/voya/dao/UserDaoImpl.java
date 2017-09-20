package com.voya.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.voya.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User findUserById(Integer id) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id =?", new Object[] { id },
					(resultSet, rowNum) -> {
						return new User(resultSet.getInt("id"), resultSet.getString("email"),
								resultSet.getString("first_name"), resultSet.getString("last_name"));
					});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public User findUserByEmail(String email) {
		try {
			User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email =?", new Object[] { email },
					(resultSet, rowNum) -> {
						return new User(resultSet.getInt("id"), resultSet.getString("email"),
								resultSet.getString("first_name"), resultSet.getString("last_name"));
					});
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean saveUser(User user) {
		return jdbcTemplate.update("INSERT INTO user (email, first_name, last_name) VALUES (?, ?, ?)",
				new Object[] { user.getEmail(), user.getFirst_name(), user.getLast_name() }) == 1;
	}

	public boolean updateUser(User user) {
		return jdbcTemplate.update("UPDATE user SET email = ?, first_name = ?, last_name = ? WHERE id = ?",
				new Object[] { user.getEmail(), user.getFirst_name(), user.getLast_name(), user.getId() }) == 1;
	}

	public boolean deleteUser(User user) {
		return jdbcTemplate.update("DELETE FROM user WHERE id= ?", new Object[] { user.getId() }) == 1;
	}

	public List<User> getAllUsers() {
		return jdbcTemplate.query("SELECT * FROM user", (resultSet, rowNum) -> {
			return new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("first_name"),
					resultSet.getString("last_name"));
		});
	}

	public void deleteAllUsers() {
		jdbcTemplate.update("DELETE * FROM user");
	}

}
