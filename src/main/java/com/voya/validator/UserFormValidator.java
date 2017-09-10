package com.voya.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.voya.domain.User;
import com.voya.service.UserService;

@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;

		if (user.getId() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "NotEmpty.userForm.first_name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "NotEmpty.userForm.last_name");
			if (!emailValidator.valid(user.getEmail())) {
				errors.rejectValue("email", "Pattern.userForm.email");
			} else if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
				errors.rejectValue("email", "Valid.userForm.email");
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "NotEmpty.userForm.first_name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "NotEmpty.userForm.last_name");
			if (!emailValidator.valid(user.getEmail())) {
				errors.rejectValue("email", "Pattern.userForm.email");
			} else if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
				errors.rejectValue("email", "Valid.userForm.email");
			}
		}

	}

}