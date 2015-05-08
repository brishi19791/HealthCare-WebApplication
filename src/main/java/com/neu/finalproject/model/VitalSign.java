package com.neu.finalproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class VitalSign {
	
	@Id @GeneratedValue
	private int EID;
	@NotNull(message="Respiratory rate cannot be null")
	@Min(value=0, message="Cannot be negative")
	private float resprate;
	@NotNull(message="Heart rate cannot be null")
	@Min(value=0, message="Cannot be negative")
	private float heartrate;
	@NotNull(message="Systolic BP cannot be null")
	@Min(value=0, message="Cannot be negative")
    private float sysbp;
	@NotNull(message="Temperature cannot be null")
	@Min(value=0, message="Cannot be negative")
    private float temp;
    
	@Valid
    @OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="EID")
    private Encounter encounter;
    
    

    public int getEID() {
		return EID;
	}

	public void setEID(int eID) {
		EID = eID;
	}

	public int getvID() {
		return EID;
	}

	public void setvID(int vID) {
		this.EID = vID;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public float getResprate() {
        return resprate;
    }

    public void setResprate(float resprate) {
        this.resprate = resprate;
    }

    public float getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(float heartrate) {
        this.heartrate = heartrate;
    }

    public float getSysbp() {
        return sysbp;
    }

    public void setSysbp(float sysbp) {
        this.sysbp = sysbp;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

}
