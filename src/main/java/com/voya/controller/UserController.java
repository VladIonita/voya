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

	// list all users
	@RequestMapping
	public String usersPage(ModelMap model) {
		model.addAttribute("listUser", userService.getUsers());
		model.addAttribute("pageTitle", "Users List");
		model.addAttribute("partial", "home");
		return "home";
	}

	// form save or update
	@RequestMapping(value = { "/edit/{id}", "/edit" }, method = RequestMethod.GET)
	public String user(@PathVariable Optional<String> id, Model model) {
		User user;
		if (id.isPresent()) {
			user = userService.findById(Integer.parseInt(id.get()));
		} else {
			user = new User();
		}
		model.addAttribute("userForm", user);
		model.addAttribute("partial", "register");
		return "index";
	}

	// save or update user
	@RequestMapping(value = { "/edit/{id}", "/edit" }, method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("partial", "register");
			return "index";
		}
		if (userService.findById(user.getId()) == null) {
			userService.save(user);
			LOGGER.info("New user " + user.getId() + " created");
		} else {
			userService.update(user);
			LOGGER.info("User " + user.getId() +" updated");
		}
		return "redirect:/";
	}

	// delete user
	@RequestMapping(value = "/userdelete/{id}", method = RequestMethod.GET)
	public String userDelete(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id);
		userService.deleteUser(user);
		LOGGER.info("User " + user.getId() +" deleted");
		return "redirect:/";
	}

	// show user profile
	@RequestMapping(value = "/userprofile/{id}", method = RequestMethod.GET)
	public String UserFormProfile(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("listAccount", accountService.getAccount(user));
		return "userprofile";
	}

	// create account
	@RequestMapping(value = "/userprofile/{id}/createaccount", method = RequestMethod.GET)
	public String createAccount(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		Account account = new Account(user);
		accountService.saveOrUpdate(account);
		model.addAttribute("userForm", user);
		model.addAttribute("listAccount", accountService.getAccount(user));
		LOGGER.info("User "  + user.getId() + " created new account with id " + account.getId() );
		return "redirect:/userprofile/" + user.getId();
	}

}
