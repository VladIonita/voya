package com.voya.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class User {

	private Integer id;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String first_name;

	@NotNull
	private String last_name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public boolean isNew() {
		return (this.id == null);
	}

}
