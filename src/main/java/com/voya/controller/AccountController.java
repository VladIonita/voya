package com.voya.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voya.domain.Account;
import com.voya.domain.User;
import com.voya.service.AccountService;
import com.voya.service.UserService;
import com.voya.service.UserServiceImpl;
import com.voya.validator.AccountFormValidator;

@Controller
@RequestMapping(value = "/userprofile")
public class AccountController {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountFormValidator accountFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(accountFormValidator);
	}

	private Account accountNew;

	@RequestMapping(value = "/{id}/deposit", method = RequestMethod.GET)
	public String showDepositForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("listAccount", findAccountByUser(findUserById(id)));
		model.addAttribute("depositForm", new Account());
		model.addAttribute("partial", "deposit");
		return "index";
	}

	@RequestMapping(value = "/{id}/deposit", method = RequestMethod.POST)
	public String showDepositForm(@PathVariable int id, @ModelAttribute("depositForm") @Validated Account accountg,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("listAccount", findAccountByUser(findUserById(id)));
			model.addAttribute("partial", "deposit");
			return "index";
		}
		findAccountAddToBalanceAndSave(Integer.parseInt(accountg.getAccount()), accountg.getDep());
		LOGGER.info("User " + id + " deposit " + accountg.getDep() + " in to account "
				+ Integer.parseInt(accountg.getAccount()));
		return "redirect:/userprofile/" + id;
	}

	@RequestMapping(value = "/{id}/transfer", method = RequestMethod.GET)
	public String showTransferForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("listAccount", findAccountByUser(findUserById(id)));
		model.addAttribute("depositForm", new Account());
		model.addAttribute("partial", "transfer");
		return "index";
	}

	@RequestMapping(value = "/{id}/transfer", method = RequestMethod.POST)
	public String showTransferForm(@PathVariable("id") int id,
			@ModelAttribute("depositForm") @Validated Account account, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("listAccount", findAccountByUser(findUserById(id)));
			model.addAttribute("partial", "transfer");
			return "index";
		}
		findAccountAddToBalanceAndSave(Integer.parseInt(account.getTo()), account.getDep());
		findAccountDecreasesBalanceAndSave(Integer.parseInt(account.getAccount()), account.getDep());
		LOGGER.info("User " + id + " transfer " + account.getDep() + " from account " + account.getAccount()
				+ " in to account " + account.getTo());
		return "redirect:/userprofile/" + id;
	}

	private User findUserById(int id) {
		return userService.findById(id);
	}

	private List<Account> findAccountByUser(User user) {
		return accountService.getAccount(user);
	}
	
	private void findAccountById(int id) {
		accountNew = accountService.findById(id);
	}

	private void findAccountAddToBalanceAndSave(int id, int deposit) {
		findAccountById(id);
		accountNew.setBalance(accountNew.getBalance() + deposit);
		accountService.saveOrUpdate(accountNew);
	}

	private void findAccountDecreasesBalanceAndSave(int id, int deposit) {
		findAccountById(id);
		accountNew.setBalance(accountNew.getBalance() - deposit);
		accountService.saveOrUpdate(accountNew);
	}

}
