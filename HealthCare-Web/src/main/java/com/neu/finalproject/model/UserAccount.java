package com.neu.finalproject.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table
public class UserAccount {

	
	
	//@GenericGenerator(name="newGenerator", strategy="foreign", parameters={@Parameter(value="employee",name="property")})
	@Id	@GeneratedValue
	private int empID;
	//@Column(unique=true)
	@NotEmpty(message="User Name cannot be null")
	private String userName;
	@NotEmpty(message="Password cannot be null")
	@Size(min=4, max=10,message="Password length sholud be {min} to {max} characters")
	private String password;
	
	private String role;
	
	public UserAccount(){}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="EMPLOYEE_ID")
	 private Employee employee;
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
