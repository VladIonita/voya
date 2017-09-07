package com.voya.domain;

import java.util.Random;

import javax.validation.constraints.NotNull;

public class Account {

	private Integer id;

	private User user;

	@NotNull
	private String account;

	private Integer balance;

	private final String currency = "RON";

	public Account(User user) {
		this.account = randomAccountGeneration();
		this.user = user;
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
	//[RO][VV][BBBB][CCCCCCCCCCCCCCCC] 
	public String randomAccountGeneration() {
		System.out.println("am intrat in random");
		StringBuilder s = new StringBuilder();
		System.out.println(s.toString());
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] chars2 = "0123456789".toCharArray();
		Random rnd = new Random();
		
		s.append("RO");
		System.out.println(s.toString());
		s.append(10 + rnd.nextInt(89));
		System.out.println(s.toString());
		for (int i = 0; i < 4; i++) {
		    s.append(chars[rnd.nextInt(chars.length)]);
		}
		System.out.println(s.toString());
		for (int i = 0; i < 16; i++) {
		    s.append(chars2[rnd.nextInt(chars2.length)]);
		}
		System.out.println(s.toString());
		return s.toString();
	}

}
