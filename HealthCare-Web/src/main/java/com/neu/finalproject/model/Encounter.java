package com.neu.finalproject.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Encounter {
	
public Encounter(){}
	
	@Id @GeneratedValue
	@Column(name="EID")
	private int encounterID;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn
	private Medication medication;
	
	@OneToOne(cascade=CascadeType.ALL)	
	@JoinColumn
	private VitalSign vitalSign;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn
	private LabTest labTest;
	
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private Patient patient;
	
	
	
	public int getEncounterID() {
		return encounterID;
	}
	public void setEncounterID(int encounterID) {
		this.encounterID = encounterID;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Medication getMedication() {
		return medication;
	}
	public void setMedication(Medication medication) {
		this.medication = medication;
	}
	public VitalSign getVitalSign() {
		return vitalSign;
	}
	public void setVitalSign(VitalSign vitalSign) {
		this.vitalSign = vitalSign;
	}
	public LabTest getLabTest() {
		return labTest;
	}
	public void setLabTest(LabTest labTest) {
		this.labTest = labTest;
	}
	
	
	
	
}
