package com.voya.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.voya.domain.Account;
import com.voya.service.AccountService;

@Component
public class AccountFormValidator implements Validator {

	@Autowired
	AccountService accountService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;

		if (account.getDep() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dep", "NotEmpty.depositForm.dep");
		} else if (account.getTo() != null) {
			if (account.getDep() > accountService.findAccountById(Integer.parseInt(account.getAccount())).getBalance()) {
				errors.rejectValue("dep", "Valid.depositForm.dep");
			}
		}

	}

}