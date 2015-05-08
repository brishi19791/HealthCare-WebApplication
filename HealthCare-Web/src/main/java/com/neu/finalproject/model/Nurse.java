package com.neu.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table
@PrimaryKeyJoinColumn(name="EMPLOYEE_ID")
public class Nurse  extends Employee{

}
