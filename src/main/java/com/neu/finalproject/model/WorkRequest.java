package com.neu.finalproject.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class WorkRequest {
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="WorkRequest_ID")
	private int wID;
	
    private String sender;
    private String receiver;
    private String message;
    private String requestDate;
    private String status;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@OneToOne(cascade=CascadeType.ALL)
	private Encounter encounter;
       
    public WorkRequest(){}
    
    public int getwID() {
		return wID;
	}
	public void setwID(int wID) {
		this.wID = wID;
	}
	
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="empID")
//    private UserAccount userAccount;
//    
//	public UserAccount getUserAccount() {
//		return userAccount;
//	}
//	public void setUserAccount(UserAccount userAccount) {
//		this.userAccount = userAccount;
//	}
	
	
	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	
    

}
