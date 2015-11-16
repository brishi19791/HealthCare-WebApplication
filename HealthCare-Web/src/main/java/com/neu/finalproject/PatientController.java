package com.neu.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.finalproject.dao.EmployeeDao;
import com.neu.finalproject.dao.PatientDao;
import com.neu.finalproject.dao.PharmacistDao;
import com.neu.finalproject.model.Combined;
import com.neu.finalproject.model.Drug;
import com.neu.finalproject.model.Employee;
import com.neu.finalproject.model.Patient;
import com.neu.finalproject.model.UserAccount;
import com.neu.finalproject.validator.UserValidator;

@Controller
public class PatientController {
	
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	
	 @Autowired
	  private EmployeeDao employeeDao;
	 
	 @Autowired
	  private PatientDao patientDao;
	 
	 
	 @Autowired
	  private PharmacistDao pharmacistDao;
	 
	 @Autowired
	 @Qualifier("employeeValidator")
	 private Validator validator;
	 
//	 @InitBinder
//	 public void initBinder(WebDataBinder binder){
//		 binder.setValidator(validator);
//		 
//	 }
//	 
	 @RequestMapping(value = "/register", method = RequestMethod.GET)
	    public String register(Model model,HttpServletRequest request) {
	    	HttpSession session = request.getSession();
		    session.setAttribute("users", new UserAccount());
	    	ArrayList<UserAccount> employeeList = employeeDao.getDoctors();
			model.addAttribute("eList", employeeList);
			model.addAttribute("combined",new Combined());
	        
	        return "registration1";
	    }
	 
	    @RequestMapping(value= "/addpatient", method = RequestMethod.POST)
	    public String addEmployee(@Valid @ModelAttribute("combined")Combined combined, BindingResult result,Model model,HttpServletRequest request){
	        if(result.hasErrors()){
	        	//System.out.println("HIIIIIII");
	        	ArrayList<UserAccount> employeeList = employeeDao.getDoctors();
				model.addAttribute("eList", employeeList);
	        	return "registration1";
	        }
	        else{
	    	try {
//	    		HttpSession session = request.getSession();
//	        	users=(UserAccount)session.getAttribute("user1");
	        	//System.out.println("HIIIIIIIIIIIIIIIIIII");
	    		
				employeeDao.addPatient(combined.getPatient(),combined.getUserAccount());
				ArrayList<UserAccount> employeeList = employeeDao.getDoctors();
				model.addAttribute("eList", employeeList);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           return "patientregistration";
	        }
	         
	         
	    }
	 
	  @RequestMapping(value="/medication",method = RequestMethod.GET)
		public String showEmployees(Model model,  HttpServletRequest request,@RequestParam String encounterID){
			//HttpSession session = request.getSession();
		  model.addAttribute("encounterID", encounterID);
		  ArrayList<Drug> drugs=pharmacistDao.showDrugs();
		  model.addAttribute("drugs", drugs);
			
			return "medication";
			
		}


	  @RequestMapping(value="/prescribe" ,method = RequestMethod.GET)
	   	public String prescribe(Model model, HttpServletRequest request,@RequestParam String encounterID,@RequestParam String drugName,@RequestParam String dosage, @RequestParam String quantity){
	   		
	   		
	   	    try {
	   	    	
	   	    patientDao.prescription(encounterID, drugName, dosage, quantity);
	   	 ArrayList<Drug> drugs=pharmacistDao.showDrugs();
		  model.addAttribute("drugs", drugs);
	   	    } catch (Exception e) {
	   	    	e.printStackTrace();
	   	    }
	   		
	   		return "medication";
	   	}

}
