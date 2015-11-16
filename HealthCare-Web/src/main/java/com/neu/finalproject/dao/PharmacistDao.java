package com.neu.finalproject.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.neu.finalproject.model.Drug;
import com.neu.finalproject.model.Employee;
import com.neu.finalproject.model.UserAccount;

@Repository
public class PharmacistDao extends DAO {
	
	public void addDrug(Drug drug) throws Exception{
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//System.out.println("useerrrrrrrrrrrr"+drug.getDrugName());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
   		String requestDate = sdf.format(new Date()); 
   		drug.setManufactureDate(requestDate);
   		
		session.persist(drug);
  
        tx.commit();
		close();
	
}
	

	public ArrayList<Drug> showDrugs() {
		ArrayList<Drug> drugList = new ArrayList();
		Query q = getSession().createQuery("from Drug");
		drugList = (ArrayList<Drug>)q.list();
		//System.out.println("Number of employees is "+drugList.size());
		close();
		return drugList;
	}

}
