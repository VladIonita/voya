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

import com.voya.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User fById(Integer id) {
		try {
			String sqlQuery = "SELECT * FROM user WHERE id =?";
			Object[] args = new Object[] { id };
			User user = jdbcTemplate.queryForObject(sqlQuery, args, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setFirst_name(rs.getString("first_name"));
					user.setLast_name(rs.getString("last_name"));
					return user;
				}
			});
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public User findbyemail(String email) {
		try {
			String sqlQuery = "SELECT * FROM user WHERE email =?";
			Object[] args = new Object[] { email };
			User user = jdbcTemplate.queryForObject(sqlQuery, args, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setFirst_name(rs.getString("first_name"));
					user.setLast_name(rs.getString("last_name"));
					return user;
				}
			});
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean save(User user) {
		String sqlQuery = "INSERT INTO user (email, first_name, last_name) VALUES (?, ?, ?)";
		Object[] args = new Object[] { user.getEmail(), user.getFirst_name(), user.getLast_name() };
		return jdbcTemplate.update(sqlQuery, args) == 1;
	}

	public boolean update(User user) {
		String sqlQuery = "UPDATE user SET email = ?, first_name = ?, last_name = ? WHERE id = ?";
		Object[] args = new Object[] { user.getEmail(), user.getFirst_name(), user.getLast_name(), user.getId() };
		return jdbcTemplate.update(sqlQuery, args) == 1;
	}

	public boolean delete(User user) {
		String sql = "DELETE FROM user WHERE id= ?";
		Object[] args = new Object[] { user.getId() };
		return jdbcTemplate.update(sql, args) == 1;
	}

	public List<User> getAllUsers() {
		return jdbcTemplate.query("SELECT * FROM user", new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				return user;
			}
		});
	}

	public void deleteAllUser() {
		String sql = "DELETE * FROM user";
		jdbcTemplate.update(sql);
	}

}
