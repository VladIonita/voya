package com.voya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.voya.domain.Account;
import com.voya.domain.User;
import com.voya.service.AccountService;
import com.voya.service.UserService;

@Controller
@RequestMapping(value = "/userprofile")
public class AccountController {

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	// show user profile
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String UserFormProfile(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("listAccount", accountService.getAccount(user));

		return "userprofile";
	}

	// create account
	@RequestMapping(value = "/{id}/createaccount", method = RequestMethod.GET)
	public String createAccount(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		Account account = new Account(user);
		accountService.saveOrUpdate(account);
		model.addAttribute("userForm", user);
		model.addAttribute("listAccount", accountService.getAccount(user));
		return "redirect:/userprofile/" + user.getId();
	}

	// show deposit
	@RequestMapping(value = "/{id}/deposit", method = RequestMethod.GET)
	public String showDepositForm(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("listAccount", accountService.getAccount(user));
		Account account = new Account();
		model.addAttribute("depositForm", account);
		model.addAttribute("partial", "deposit");
		return "index";
	}

	// deposit
	@RequestMapping(value = "/{id}/deposit", method = RequestMethod.POST)
	public String showDepositForm(@PathVariable int id, @RequestParam("dep") Integer dep,
			@ModelAttribute("depositForm") Account accountg, Model model) {
		Account account = accountService.findById(Integer.parseInt(accountg.getAccount()));
		User user = userService.findById(id);
		if (dep == (int) dep) {
			if (dep > 0) {
				account.setBalance(account.getBalance() + dep);
				accountService.saveOrUpdate(account);
			}
		}
		return "redirect:/userprofile/" + user.getId();
	}

	// show transfer
	@RequestMapping(value = "/{id}/transfer", method = RequestMethod.GET)
	public String showTransferForm(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("listAccount", accountService.getAccount(user));
		Account account = new Account();
		model.addAttribute("tranForm", account);
		model.addAttribute("partial", "transfer");
		return "index";
	}

	// transfer
	@RequestMapping(value = "/{id}/transfer", method = RequestMethod.POST)
	public String showTransferForm(@PathVariable("id") int id, @RequestParam("dep") Integer dep,
			@ModelAttribute("tranForm") Account account, Model model) {
		Account accountNew = accountService.findById(Integer.parseInt(account.getTo()));
		User user = userService.findById(id);
		if (dep == (int) dep) {
			if (dep > 0) {
				accountNew.setBalance(accountNew.getBalance() + dep);
				accountService.saveOrUpdate(accountNew);
			}
		}
		return "redirect:/userprofile/" + user.getId();
	}
}
