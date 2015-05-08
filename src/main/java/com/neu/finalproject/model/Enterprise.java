package com.neu.finalproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Enterprise {

	public Enterprise(){}

	@Id @GeneratedValue
	private int enterpriseID;
	private String enterpriseName;
	
	
	public int getEnterpriseID() {
		return enterpriseID;
	}
	public void setEnterpriseID(int enterpriseID) {
		this.enterpriseID = enterpriseID;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	
}
