package com.neu.finalproject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.finalproject.dao.EmployeeDao;
import com.neu.finalproject.dao.PharmacistDao;
import com.neu.finalproject.model.Drug;
import com.neu.finalproject.model.UserAccount;


@Controller
public class PharmacistController {

	

	  @Autowired
	  private EmployeeDao employeeDao;
	  
	  @Autowired
	  private PharmacistDao pharmacistDao;
	  
	  
	  
	   @RequestMapping(value= "/adddrug", method = RequestMethod.POST)
	    public String addDrug(@Valid @ModelAttribute("drug")Drug drug,BindingResult result, HttpServletRequest request){
	         if(result.hasErrors()){
	        	 return "pharmacistworkarea";
	         }
	         else{
	            try {
	            	//HttpSession session = request.getSession();
	            	//user=new UserAccount();
					pharmacistDao.addDrug(drug);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	        return "drugsuccess";
	         }
	         
	    }
	   
	   
	    @RequestMapping(value = "/adddrug1", method = RequestMethod.GET)
	    public String adddrug(Model model,HttpServletRequest request) {
	    	
	    	HttpSession session = request.getSession();
	   //model.addAttribute("e", new UserAccount());
	    	 model.addAttribute("drug",new Drug());
	        return "pharmacistworkarea";
	    } 
	    
}
