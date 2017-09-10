package com.voya.domain;

import java.util.Random;

import javax.validation.constraints.NotNull;

public class Account {

	private Integer id;

	private User user;

	@NotNull
	private String account;

	@NotNull
	private Integer balance;

	private final String currency = "RON";

	@NotNull
	private String from;

	@NotNull
	private String to;

	@NotNull
	private Integer dep;

	public Account() {
	}

	public Account(User user) {
		this.account = randomAccountGeneration();
		this.user = user;
	}

	public Account(Integer id, User user, String account, Integer balance) {
		this.id = id;
		this.user = user;
		this.account = account;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Integer getDep() {
		return dep;
	}

	public void setDep(Integer dep) {
		this.dep = dep;
	}

	// [RO][VV][BBBB][CCCCCCCCCCCCCCCC]
	public String randomAccountGeneration() {
		StringBuilder s = new StringBuilder();
		System.out.println(s.toString());
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] chars2 = "0123456789".toCharArray();
		Random rnd = new Random();
		s.append("RO");
		s.append(10 + rnd.nextInt(89));
		for (int i = 0; i < 4; i++) {
			s.append(chars[rnd.nextInt(chars.length)]);
		}
		for (int i = 0; i < 16; i++) {
			s.append(chars2[rnd.nextInt(chars2.length)]);
		}
		return s.toString();
	}
}
