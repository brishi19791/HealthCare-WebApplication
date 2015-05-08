package com.neu.finalproject.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.finalproject.model.Encounter;



public class UserValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Encounter.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

}
