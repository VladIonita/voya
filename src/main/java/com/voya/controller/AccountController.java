package com.voya.controller;

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
	public String showDepositForm(@PathVariable int id, @ModelAttribute("depositForm") @Validated Account accountg,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			User user = userService.findById(id);
			model.addAttribute("listAccount", accountService.getAccount(user));
			model.addAttribute("partial", "deposit");
			return "index";
		}
		Account account = accountService.findById(Integer.parseInt(accountg.getAccount()));
		User user = userService.findById(id);
		account.setBalance(account.getBalance() + accountg.getDep());
		accountService.saveOrUpdate(account);
		LOGGER.info("User " + user.getId() + " deposit " + accountg.getDep() + " in to account " + account.getId());
		return "redirect:/userprofile/" + user.getId();
	}

	// show transfer
	@RequestMapping(value = "/{id}/transfer", method = RequestMethod.GET)
	public String showTransferForm(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("listAccount", accountService.getAccount(user));
		Account account = new Account();
		model.addAttribute("depositForm", account);
		model.addAttribute("partial", "transfer");
		return "index";
	}

	// transfer
	@RequestMapping(value = "/{id}/transfer", method = RequestMethod.POST)
	public String showTransferForm(@PathVariable("id") int id,
			@ModelAttribute("depositForm") @Validated Account account, BindingResult result, Model model) {
		if (result.hasErrors()) {
			User user = userService.findById(id);
			model.addAttribute("listAccount", accountService.getAccount(user));
			model.addAttribute("partial", "transfer");
			return "index";
		}
		Account accountNew = accountService.findById(Integer.parseInt(account.getTo()));
		User user = userService.findById(id);
		accountNew.setBalance(accountNew.getBalance() + account.getDep());
		accountService.saveOrUpdate(accountNew);
		Account accountOld = accountService.findById(Integer.parseInt(account.getAccount()));
		accountOld.setBalance(accountOld.getBalance() - account.getDep());
		accountService.saveOrUpdate(accountOld);
		LOGGER.info("User " + user.getId() + " transfer " + account.getDep() + " from account " + account.getAccount()
				+ " in to account " + account.getTo());
		return "redirect:/userprofile/" + user.getId();
	}
}
