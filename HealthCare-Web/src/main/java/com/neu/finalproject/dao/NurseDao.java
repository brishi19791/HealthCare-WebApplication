package com.neu.finalproject.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.neu.finalproject.model.Employee;

@Repository
public class NurseDao extends DAO {

	public ArrayList<Employee> showPatients() {
		ArrayList<Employee> patientList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = getSession().createQuery("from Patient");
		//q.setString("username", userName);
		patientList = (ArrayList<Employee>)q.list();
		//System.out.println("Number of messages is "+patientList.size());
		close();
		return patientList;
	}
}
