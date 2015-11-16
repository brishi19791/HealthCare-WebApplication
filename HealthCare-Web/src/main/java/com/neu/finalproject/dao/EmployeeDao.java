package com.neu.finalproject.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.neu.finalproject.model.Employee;
import com.neu.finalproject.model.Encounter;
import com.neu.finalproject.model.LabTest;
import com.neu.finalproject.model.LabWorkRequest;
import com.neu.finalproject.model.Patient;
import com.neu.finalproject.model.UserAccount;
import com.neu.finalproject.model.VitalSign;
import com.neu.finalproject.model.WorkRequest;

@Repository
public class EmployeeDao extends DAO{

	
	public void addEmployee(UserAccount user) throws Exception{
		
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			//System.out.println("useerrrrrrrrrrrr"+user);
			
			session.persist(user);
      
	        tx.commit();
			session.close();
		
	}
	
	public Boolean employeeCheck(UserAccount userAccount1) throws Exception{
		
		
		ArrayList<UserAccount> users = new ArrayList();
		Query q = getSession().createQuery("from UserAccount");
		//q.setString("username", userName);
		users = (ArrayList<UserAccount>)q.list();
		for(UserAccount u:users){
    		System.out.println("given"+userAccount1.getUserName()+"actual"+u.getUserName());
    		if(userAccount1.getUserName().equalsIgnoreCase(u.getUserName())){
    			//model.addAttribute("error", "");
    			
    			return false;
    		}
		}
		return true;
   
}

	
	public void addPatient(Patient patient,UserAccount user) throws Exception{
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//System.out.println("patient name"+patient.getFirstName());
		user.setEmployee(patient);
		user.setRole("Patient");
		
        session.persist(user);
        //p.setUserAccount(userAccount);
        tx.commit();
		close();
	
}
	public UserAccount queryEmployeeByNameAndPassword(String userName, String password)
            throws Exception {
		try {
			
            Query q = getSession().createQuery("from UserAccount where userName = :userName and password = :password");
            //System.out.println("risssssssssssssss"+userName);
            q.setString("userName", userName);
            q.setString("password", password);
            
            UserAccount userAccount = (UserAccount) q.uniqueResult();
            //System.out.println("USSSS"+userAccount.getUserName());
            if(userAccount==null){
            	System.out.println("no userrrr");
            }
            close();
            return userAccount;
		} catch (HibernateException e) {
			
            throw new Exception("Could not get user " + userName, e);
		}	
    }
	
	public ArrayList<Employee> showEmployees() {
		ArrayList<Employee> employeeList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = getSession().createQuery("from Employee");
		//q.setString("username", userName);
		employeeList = (ArrayList<Employee>)q.list();
		System.out.println("Number of employees is "+employeeList.size());
		close();
		return employeeList;
	}
	
	public ArrayList<UserAccount> getUserAccount() {
		ArrayList<UserAccount> employeeList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = getSession().createQuery("from UserAccount");
		//q.setString("username", userName);
		employeeList = (ArrayList<UserAccount>)q.list();
		System.out.println("Number of employees is "+employeeList.size());
		close();
		return employeeList;
	}
	
	public ArrayList<Encounter> showEncounterList() {
		ArrayList<Encounter> encounterList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = getSession().createQuery("from Encounter");
		//q.setString("username", userName);
		encounterList = (ArrayList<Encounter>)q.list();
		System.out.println("Number of encounters "+encounterList.size());
		close();
		return encounterList;
	}
	
	public ArrayList<Patient> showPatients() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		ArrayList<UserAccount> uaList = new ArrayList();
		ArrayList<Patient> patientList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = session.createQuery("from UserAccount where role= :Patient");
		q.setString("Patient", "Patient");
		uaList = (ArrayList<UserAccount>)q.list();
		for(UserAccount ua: uaList){
			int empID=ua.getEmployee().getEmpID();
			//System.out.println("Patient LISTTTTT"+empID);
			Query q1=session.createQuery("from Patient where empID= :empID");
			q1.setInteger("empID", empID);
			Patient p=(Patient)q1.uniqueResult();
			patientList.add(p);
		}
		//System.out.println("Number of messages is "+patientList.size());
		tx.commit();
		close();
		return patientList;
	}
	
	public ArrayList<UserAccount> getDoctors() {
		ArrayList<UserAccount> eList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = getSession().createQuery("from UserAccount where role= :doctor");
		q.setString("doctor", "doctor");
		eList = (ArrayList<UserAccount>)q.list();
		//System.out.println("Number of Doctors is "+eList.size());
		close();
		return eList;
	}
	
	public ArrayList<UserAccount> getLabAssistants() {
		ArrayList<UserAccount> eList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = getSession().createQuery("from UserAccount where role= :labassistant");
		q.setString("labassistant", "labassistant");
		eList = (ArrayList<UserAccount>)q.list();
		//System.out.println("Number of Lab Assistants is "+eList.size());
		close();
		return eList;
	}
	public ArrayList<Patient> getpatientfordoctor(String userName) {
		ArrayList<Patient> eList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = getSession().createQuery("from Patient where primaryDoctor= :userName");
		q.setString("userName", userName);
		eList = (ArrayList<Patient>)q.list();
		//System.out.println("Number of messages is "+eList.size());
		close();
		return eList;
	}
	
	public ArrayList<LabWorkRequest> getLabWorkRequest() {
		ArrayList<LabWorkRequest> lwrList = new ArrayList();
		//System.out.println("User name is "+userName);
		Query q = getSession().createQuery("from LabWorkRequest");
		lwrList = (ArrayList<LabWorkRequest>)q.list();
		//System.out.println("Number of LabWorkRequests is "+lwrList.size());
		close();
		return lwrList;
	}
	
	
	public void deleteemployees(String[] empID) throws Exception{
		
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			for(String eID : empID) {
				Transaction tx = session.beginTransaction();
				//Transaction transaction = getSession().beginTransaction();
			
				//String hql = "delete from  Employee where empID = :eID";
				//String hq2 = "delete from  UserAccount where empID = :eID";
				String hql = "delete from  Employee where empID = :eID";
				//Query query = session.createQuery(hql);
				//Query query1 = session.createQuery(hq2);
				Query query = session.createQuery(hql);
				query.setString("eID",eID);
				
				//query1.setString("eID",eID);
				//int rowCount = query.executeUpdate();
				System.out.println("eeeeeeeeee");
				//int rowCount1 = query1.executeUpdate();
				int rowCount = query.executeUpdate();
			    tx.commit();
			}
			close();
		
	}
	
	public void addVitalSign(float resprate, float heartrate, float sysbp, float temp, int empID) throws Exception{
		try {
			//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session =HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			//Transaction transaction = getSession().beginTransaction();
			Query q = session.createQuery("from Patient where empID = :empID");
			q.setInteger("empID", empID);
			Patient p=(Patient)q.uniqueResult();
			//System.out.println("Patient name"+p.getFirstName());
			Encounter encounter=new Encounter();
			encounter.setPatient(p);
			VitalSign vs=new VitalSign();
			vs.setResprate(resprate);
			vs.setHeartrate(heartrate);
			vs.setSysbp(sysbp);
			vs.setTemp(temp);
			encounter.setVitalSign(vs);
			//encounter.setEncounterID(p.getEmpID());
			//nwr.setEncounter(encounter);
			p.getEncounterList().add(encounter);
		
			session.save(vs);
			session.save(p);
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			throw new Exception(e);
		}	
	}
		public void sendtolab(String testType, String receiver,String sender,String message, String requestDate,String encounterID) throws Exception{
			//try {
				//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session =HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			//Transaction transaction = getSession().beginTransaction();
				//System.out.println("HELLLLLLLLLLPPPPPPPP");
				Query q = session.createQuery("from Encounter where EID=:encounterID");
				int i=Integer.parseInt(encounterID);
				q.setInteger("encounterID", i);
				Encounter encounter=(Encounter)q.uniqueResult();
				//System.out.println("Encounter ID"+encounter.getEncounterID());
				LabWorkRequest lwr=new LabWorkRequest();
				lwr.setReceiver(receiver);
				lwr.setSender(sender);
				lwr.setRequestDate(requestDate);
				lwr.setMessage(message);
				lwr.setTestType(testType);
				lwr.setStatus("Awaiting");
				LabTest labTest=new LabTest();
				labTest.setTestType(testType);
				labTest.setTestStatus("Awaiting");;
				encounter.setLabTest(labTest);
				lwr.setEncounter(encounter);
				session.save(lwr);
				tx.commit();
				session.close();
				
				
				
	}
	
	
	

	
	
}
