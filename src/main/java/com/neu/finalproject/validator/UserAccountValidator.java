package com.neu.finalproject.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.neu.finalproject.model.UserAccount;

public class UserAccountValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserAccount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

}
