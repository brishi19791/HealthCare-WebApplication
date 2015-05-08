package com.neu.finalproject.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table
@Inheritance(strategy=InheritanceType.JOINED)
public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Employee(){}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EMPLOYEE_ID")
	private int empID;
	
	@NotEmpty(message="First name cannot be null")
	@Size(min=4, max=10, message="please enter length {min} and {max} fist name")
	private String firstName;
	
	@NotEmpty(message="Last name cannot be null")
	private String lastName;
	
	@NotNull(message="Age cannot be null")
	@Max(value=100,message="enter valid age")
	@Min(value=0,message="age cannot be less than 10")
	private Integer age;
	
	@NotEmpty(message="Email cannot be null")
	@Email(message="Enter valid email id")
	private String email;
	
	@NotEmpty(message="City cannot be null")
	private String city;
//	@Column(nullable=false)
	@Size(min=4, max=10, message="please enter valid Phone number")
	@NotEmpty(message="Phone number cannot be null")
	private String phNum;
	
	
////
//	@OneToOne(cascade = CascadeType.ALL)
//	private UserAccount userAccount;
//	
//	
//	
//	public UserAccount getUserAccount() {
//		return userAccount;
//	}
//	public void setUserAccount(UserAccount userAccount) {
//		this.userAccount = userAccount;
//	}
	public String getPhNum() {
		return phNum;
	}
	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}
	
	
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	
	
}
