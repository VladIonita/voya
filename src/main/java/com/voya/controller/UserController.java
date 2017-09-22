package com.voya.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import com.voya.validator.UserFormValidator;

@Controller
public class UserController {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserFormValidator userFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	User user;
	Account account;

	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String listAllUsersOnPage(ModelMap model) {
		model.addAttribute("listUser", userService.getAllUsers());
		model.addAttribute("pageTitle", "Users List");
		model.addAttribute("partial", "home");
		return "home";
	}

	@RequestMapping(value = { "/edit/{id}", "/edit" }, method = RequestMethod.GET)
	public String userFormPage(@PathVariable Optional<String> id, Model model) {
		checkIfIdIsPresent(id);
		model.addAttribute("userForm", user);
		model.addAttribute("partial", "register");
		return "index";
	}

	@RequestMapping(value = { "/edit/{id}", "/edit" }, method = RequestMethod.POST)
	public String userFormPage(@ModelAttribute("userForm") @Validated User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("partial", "register");
			return "index";
		}
		if (findUserById(user.getId()) == null) {
			userService.saveUser(user);
			LOGGER.info("New user " + user.getId() + " created");
		} else {
			userService.updateUser(user);
			LOGGER.info("User " + user.getId() + " updated");
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/userdelete/{id}", method = RequestMethod.GET)
	public String userDelete(@PathVariable("id") Integer id, Model model) {
		findUserById(id);
		userService.deleteUser(user);
		LOGGER.info("User " + user.getId() + " deleted");
		return "redirect:/";
	}

	@RequestMapping(value = "/userprofile/{id}", method = RequestMethod.GET)
	public String userProfile(@PathVariable("id") int id, Model model) {
		findUserById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("listAccount", accountService.getAccountsByUser(user));
		return "userprofile";
	}

	@RequestMapping(value = "/userprofile/{id}/createaccount", method = RequestMethod.GET)
	public String createAccount(@PathVariable("id") int id, Model model) {
		findUserById(id);
		account = new Account(user);
		accountService.saveOrUpdate(account);
		model.addAttribute("userForm", user);
		model.addAttribute("listAccount", accountService.getAccountsByUser(user));
		LOGGER.info("User " + user.getId() + " created new account with id " + account.getId());
		return "redirect:/userprofile/" + user.getId();
	}

	private void checkIfIdIsPresent(Optional<String> id) {
		if (id.isPresent()) {
			user = userService.findUserById(Integer.parseInt(id.get()));
		} else {
			user = new User();
		}
	}

	private User findUserById(Integer id) {
		user = userService.findUserById(id);
		return user;
	}

}
