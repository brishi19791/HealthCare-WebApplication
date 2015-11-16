package com.neu.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.finalproject.dao.EmployeeDao;
import com.neu.finalproject.dao.PatientDao;
import com.neu.finalproject.model.LabWorkRequest;
import com.neu.finalproject.model.Patient;
import com.neu.finalproject.model.UserAccount;

@Controller
public class LabController {
	
	 @Autowired
	  private EmployeeDao employeeDao;
	 
	 @Autowired
	  private PatientDao patientDao;
	
	 @RequestMapping(value="/diagnose",method = RequestMethod.GET)
	    public String diagnose(Model model,HttpServletRequest request,@RequestParam String encounterID){
	    	HttpSession session = request.getSession();
		    //session.setAttribute("user1", new UserAccount());
	    	ArrayList<UserAccount> employeeList = employeeDao.getLabAssistants();
			model.addAttribute("labList", employeeList);
			model.addAttribute("encounterID", encounterID);
			return "diagnose";
		}
	 
	 @RequestMapping(value="/sendtolab",method = RequestMethod.GET)
	   	public String sendtolab(Model model, HttpServletRequest request,@RequestParam String encounterID,@RequestParam String testType,@RequestParam String receiver,@RequestParam String message){
	   		HttpSession session = request.getSession();
	   		UserAccount ua=(UserAccount)session.getAttribute("userAccount");
	   		String sender=ua.getUserName();
	   		//String message=request.getParameter("message");
	   		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	   		String requestDate = sdf.format(new Date()); 
	   		
	   	    try {
	   	    employeeDao.sendtolab(testType, receiver, sender, message, requestDate,encounterID);
	   	 ArrayList<UserAccount> employeeList = employeeDao.getLabAssistants();
	   	 model.addAttribute("labList", employeeList);
	   	    } catch (Exception e) {
	   	    	e.printStackTrace();
	   	    }
	   		
	   		return "diagnose";
	   	}
	 
	 @RequestMapping(value="/sendlabresult",method = RequestMethod.POST)
	   	public String sendlabresult(Model model, HttpServletRequest request,@RequestParam String message,@RequestParam String wID){
	   		HttpSession session = request.getSession();
	   		UserAccount ua=(UserAccount)session.getAttribute("userAccount");
	   		
	   		//String message=request.getParameter("message");
//	   		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//	   		String requestDate = sdf.format(new Date()); 
	   		
	   	    try {
	   	    patientDao.sendLabResults(message,wID);
	   	    model.addAttribute("wID",wID);
	   	 model.addAttribute("message",message);
	   	    } catch (Exception e) {
	   	    	e.printStackTrace();
	   	    }
	   		
	   		return "labresultsuccess";
	   	}
	 
	 @RequestMapping(value="/sendlabresult1",method = RequestMethod.GET)
	   	public String sendlabresult1(Model model, HttpServletRequest request,@RequestParam String message,@RequestParam String wID){
	   		HttpSession session = request.getSession();
	   		UserAccount ua=(UserAccount)session.getAttribute("userAccount");
	   		
	   		//String message=request.getParameter("message");
//	   		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//	   		String requestDate = sdf.format(new Date()); 
	   		
	   	    try { 
	   	   //patientDao.sendLabResults(message,wID);
	   	     ArrayList<LabWorkRequest> wrList=employeeDao.getLabWorkRequest();
			    model.addAttribute("wrList",wrList);
	   	    } catch (Exception e) {
	   	    	e.printStackTrace();
	   	    }
	   		
	   		return "labassisworkarea";
	   	}


	 @RequestMapping(value= "/backhome1", method = RequestMethod.POST)
	    public String back1(Model model, UserAccount user, BindingResult result, HttpServletRequest request, HttpServletResponse response){
	    	HttpSession session = request.getSession();
		    //session.setAttribute("userAccount", user);
	    	user=(UserAccount)session.getAttribute("userAccount");
		    //System.out.println("DOCTORRR"+user.getUserName());
		    ArrayList<Patient> pList=employeeDao.getpatientfordoctor(user.getUserName());
		    ArrayList<LabWorkRequest> workRequestList=employeeDao.getLabWorkRequest();
		    model.addAttribute("pList", pList);
		    model.addAttribute("workRequestList",workRequestList);
			return"doctorworkarea";
	             
	         
	    }
	 
	 @RequestMapping(value= "/backhome2", method = RequestMethod.POST)
	    public String back2(Model model, UserAccount user, BindingResult result, HttpServletRequest request, HttpServletResponse response){
	    	HttpSession session = request.getSession();
		    //session.setAttribute("userAccount", user);
	    	user=(UserAccount)session.getAttribute("userAccount");
		    //System.out.println("DOCTORRR"+user.getUserName());
		    ArrayList<Patient> pList=employeeDao.getpatientfordoctor(user.getUserName());
		    ArrayList<LabWorkRequest> workRequestList=employeeDao.getLabWorkRequest();
		    model.addAttribute("pList", pList);
		    model.addAttribute("workRequestList",workRequestList);
			return"doctorworkarea";
	             
	         
	    }
	    

}
