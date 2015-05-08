package com.neu.finalproject.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Role {

	@Id 
	@Column(name="ROLE_ID")
	private int roleID;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="role")
	private List<WorkRequest> workQueue;
	
	public Role(){}

	public List<WorkRequest> getWorkQueue() {
		return workQueue;
	}

	public void setWorkQueue(List<WorkRequest> workQueue) {
		this.workQueue = workQueue;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	
	
}
