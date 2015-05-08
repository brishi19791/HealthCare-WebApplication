package com.neu.finalproject.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
@PrimaryKeyJoinColumn(name="EMPLOYEE_ID")
public class Patient extends Employee {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message="DOJ cannot be null")
	private String doj;
	
	private String primaryDoctor;
//	
//	@Id @GeneratedValue
//	private String pID;
//	
	
	public String getPrimaryDoctor() {
		return primaryDoctor;
	}

	public void setPrimaryDoctor(String primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}
	


	@OneToMany(cascade = CascadeType.ALL, mappedBy="patient")
	private List<Encounter> encounterList;
	
	

	public List<Encounter> getEncounterList() {
		return encounterList;
	}

	public void setEncounterList(List<Encounter> encounterList) {
		this.encounterList = encounterList;
	}

	
	

}
