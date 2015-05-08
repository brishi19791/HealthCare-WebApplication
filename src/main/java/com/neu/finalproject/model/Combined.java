package com.neu.finalproject.model;

import javax.validation.Valid;

public class Combined {
	
	@Valid
	private UserAccount userAccount;
	@Valid
	private Patient patient;
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
}
