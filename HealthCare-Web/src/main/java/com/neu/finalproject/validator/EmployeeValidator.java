package com.neu.finalproject.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.finalproject.model.Employee;
import com.neu.finalproject.model.Patient;
import com.neu.finalproject.model.UserAccount;



public class EmployeeValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserAccount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserAccount user = (UserAccount)target;
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validate.userName", "Please enter UserName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validate.password", "Please enter Password");
		
	}


}
