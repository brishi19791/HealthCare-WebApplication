package com.neu.finalproject.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.neu.finalproject.model.Encounter;
import com.neu.finalproject.model.LabWorkRequest;
import com.neu.finalproject.model.Medication;
import com.neu.finalproject.model.UserAccount;

@Repository
public class PatientDao extends DAO{
	
	public void prescription(String encounterID, String drugName, String dosage, String quantity) {
		//ArrayList<UserAccount> eList = new ArrayList();
		//System.out.println("User name is "+userName);
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int i=Integer.parseInt(encounterID);
		Query q = session.createQuery("from Encounter where EID= :encounterID");
		q.setInteger("encounterID", i);
		Encounter encounter=(Encounter)q.uniqueResult();
		//System.out.println("Number of Lab Assistants is "+eList.size());
		int qua=Integer.parseInt(quantity);
		Medication medication=new Medication();
		medication.setDrugName(drugName);
		medication.setDosage(dosage);
		medication.setQuantity(qua);
		encounter.setMedication(medication);
		session.save(medication);
		session.save(encounter);
		tx.commit();
		session.close();
	}
	
	public void sendLabResults(String message,String wID) throws Exception{
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery("from LabWorkRequest where WorkRequest_ID= :wID");
				query.setString("wID",wID);
				LabWorkRequest lwr=(LabWorkRequest)query.uniqueResult();
				//System.out.println("LABWORKREQUESTTTTTTTT"+lwr.getwID());
				lwr.setMessage(message);
				lwr.setStatus("Completed!!");;
				lwr.getEncounter().getLabTest().setTestStatus("Completed");
				session.save(lwr);
				
			    tx.commit();
			
			close();
		} catch (HibernateException e) {
			throw new Exception("Could not send");
		}	
		
	}
	


}
