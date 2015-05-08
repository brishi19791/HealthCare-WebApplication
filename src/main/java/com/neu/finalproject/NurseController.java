package com.neu.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.finalproject.dao.EmployeeDao;
import com.neu.finalproject.model.Patient;
import com.neu.finalproject.model.UserAccount;

@Controller
public class NurseController {
	 @Autowired
	  private EmployeeDao employeeDao;
	
	
	
	 @RequestMapping(value="/viewpatient",method = RequestMethod.POST)
	   	public String showPatients(Model model,  HttpServletRequest request){
	   		//HttpSession session = request.getSession();
	   		
	   		ArrayList<Patient> patientList = employeeDao.showPatients();
	   		model.addAttribute("patientList", patientList);
	   		return "showpatients";
	   		
	   	}
	 
	 @RequestMapping(value="/backshowpatients",method = RequestMethod.POST)
	   	public String showPatients1(Model model,  HttpServletRequest request){
	   		//HttpSession session = request.getSession();
	   		
	   		ArrayList<Patient> patientList = employeeDao.showPatients();
	   		model.addAttribute("patientList", patientList);
	   		return "showpatients";
	   		
	   	}
	    
	    @RequestMapping(value="/vitalsignform",method = RequestMethod.GET)
	    public String sendVitalSign(Model model, @RequestParam String primaryDoctor,@RequestParam String patientID, HttpServletRequest request){
			model.addAttribute("primaryDoctor", primaryDoctor);
			model.addAttribute("patientID", patientID);
			return "vitalsignform";
		}
	    
	    @RequestMapping(value="/sendvitalsigns")
		public String sendVitalSigns(Model model, HttpServletRequest request){
			//System.out.println("I am in addMessage");
			HttpSession session = request.getSession();
			int empID=Integer.parseInt(request.getParameter("empID"));
			
			float resprate =Float.parseFloat(request.getParameter("resprate")) ;
			//String fromUser = u.getUserName();
			float heartrate = Float.parseFloat(request.getParameter("heartrate")) ;
			float sysbp=Float.parseFloat(request.getParameter("sysbp")) ;
			float temp=Float.parseFloat(request.getParameter("temp")) ;
			String to=request.getParameter("primaryDoctor");
			UserAccount ua=(UserAccount)session.getAttribute("userAccount");
			//String from=ua.getUserName();
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String date = sdf.format(new Date()); 
		    try {
		    employeeDao.addVitalSign(resprate, heartrate, sysbp, temp,empID);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
			
			return "vitalsignform";
		}
	    

}
