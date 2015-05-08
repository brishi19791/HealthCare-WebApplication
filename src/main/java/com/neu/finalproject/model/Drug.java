package com.neu.finalproject.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Drug {
	
	public Drug(){}

	@Id @GeneratedValue
	private int dID;
	
	@NotEmpty(message="Drug Name cnnot be null")
	 private String drugName;
	
	    private String manufactureDate;
	@NotEmpty(message="Expiry date cnnot be null")
	    private String expiryDate;
	@NotEmpty(message="Composition cnnot be null")
	    private String compostion;
	@NotEmpty(message="Warning cnnot be null")
	    private String warning;
	
	    
		public String getDrugName() {
			return drugName;
		}
		public void setDrugName(String drugName) {
			this.drugName = drugName;
		}
		
		public String getManufactureDate() {
			return manufactureDate;
		}
		public void setManufactureDate(String manufactureDate) {
			this.manufactureDate = manufactureDate;
		}
		public String getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(String expiryDate) {
			this.expiryDate = expiryDate;
		}
		public String getCompostion() {
			return compostion;
		}
		public void setCompostion(String compostion) {
			this.compostion = compostion;
		}
		public String getWarning() {
			return warning;
		}
		public void setWarning(String warning) {
			this.warning = warning;
		}
	    
	    
}
