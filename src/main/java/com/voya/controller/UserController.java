package com.voya.controller;

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

import com.voya.domain.User;
import com.voya.service.UserService;
import com.voya.validator.UserFormValidator;

@Controller
public class UserController {
	
	@Autowired
	UserFormValidator userFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	@Autowired
	UserService userService;

	// list all users
	@RequestMapping
	public String usersPage(ModelMap model) {
		model.addAttribute("listUser", userService.getUsers());
		model.addAttribute("pageTitle", "Users List");
		model.addAttribute("partial", "home");
		return "index";
	}

	// show add user form
	@RequestMapping(value = "/UserAdd", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		User user = new User();
		model.addAttribute("userForm", user);
		model.addAttribute("partial", "register");
		return "index";
	}

	// save or update user
	@RequestMapping(value = "/UserAdd", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("partial", "register");
			return "index";
		}
		userService.saveOrUpdate(user);

		return "redirect:/home";
	}

	// show edit form
	@RequestMapping(value = "/UserEdit/{id}", method = RequestMethod.GET)
	public String UserFormEdit(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("partial", "register");
		return "index";
	}

	// show user profile
	@RequestMapping(value = "/UserProfile/{id}", method = RequestMethod.GET)
	public String UserFormProfile(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("partial", "UserProfile");
		return "index";
	}
}
