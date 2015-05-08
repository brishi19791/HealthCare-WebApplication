package com.neu.finalproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.finalproject.dao.EmployeeDao;
import com.neu.finalproject.dao.NurseDao;
import com.neu.finalproject.model.Drug;
import com.neu.finalproject.model.Employee;
import com.neu.finalproject.model.Encounter;
import com.neu.finalproject.model.LabWorkRequest;
import com.neu.finalproject.model.Patient;
import com.neu.finalproject.model.UserAccount;
import com.neu.finalproject.model.WorkRequest;





/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	 // @Autowired
	//private EmployeeService employeeService;
	  
	  @Autowired
	  private EmployeeDao employeeDao;
	  
  
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model,HttpServletRequest request,@CookieValue(value="userName",required=false)String userName,@CookieValue(value="password",required=false)String password) {
    	HttpSession session = request.getSession();
	    session.setAttribute("user", new UserAccount());
   model.addAttribute("user", new UserAccount());
   if(userName!=null & password!=null){
	
	   try{
			//System.out.println("loginuser"+user.getUserName());
			UserAccount userAccount=employeeDao.queryEmployeeByNameAndPassword(userName,password);
			//String str = request.getParameter("remember");
	
		    System.out.println(userAccount.getUserName());
			if(userAccount.getRole().equalsIgnoreCase("admin")){
				//HttpSession session = request.getSession();
			    session.setAttribute("userAccount", userAccount);
			    UserAccount userAccount1=new UserAccount();
			    model.addAttribute("userAccount1",userAccount1);
				return"adminhome1";
				
			}
			else if(userAccount.getRole().equalsIgnoreCase("doctor")){
				//HttpSession session = request.getSession();
			    session.setAttribute("userAccount", userAccount);
			    ArrayList<Patient> pList=employeeDao.getpatientfordoctor(userAccount.getUserName());
			    ArrayList<LabWorkRequest> workRequestList=employeeDao.getLabWorkRequest();
			    model.addAttribute("pList", pList);
			    model.addAttribute("workRequestList",workRequestList);
				return"doctorworkarea";
			}
			else if(userAccount.getRole().equalsIgnoreCase("nurse")){
				//HttpSession session = request.getSession();
			    session.setAttribute("userAccount", userAccount);
				return"nurseworkarea";
			}
			else if(userAccount.getRole().equalsIgnoreCase("labassistant")){
				//HttpSession session = request.getSession();
			    session.setAttribute("userAccount", userAccount);
			    ArrayList<LabWorkRequest> wrList=employeeDao.getLabWorkRequest();
			    model.addAttribute("wrList",wrList);
				return"labassisworkarea";
			}
			else if(userAccount.getRole().equalsIgnoreCase("pharmacist")){
				//HttpSession session = request.getSession();
			    session.setAttribute("userAccount", userAccount);
			    model.addAttribute("drug",new Drug());
				return"pharmacistworkarea";
			}
			else if(userAccount.getRole().equalsIgnoreCase("patient")){
				//HttpSession session = request.getSession();
			    session.setAttribute("userAccount", userAccount);
			    model.addAttribute("userAccount",userAccount);
			    Patient p=(Patient)userAccount.getEmployee();
			    List<Encounter> encounterList=(List<Encounter>)p.getEncounterList();
			    model.addAttribute("encounterList",encounterList);
				return"patientworkarea";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	   
   }
        
        return "login";
    }
    
    @RequestMapping(value = "/backlogin", method = RequestMethod.GET)
    public String backlogin(Model model,HttpServletRequest request,HttpServletResponse response) {
    	
    	HttpSession session = request.getSession();
	    session.setAttribute("user", new UserAccount());
   model.addAttribute("user", new UserAccount());
	    Cookie loginCookie = null;
		Cookie passwordCookie = null;
        Cookie[] cookies = request.getCookies();
       
        if(cookies != null) {
        	for(Cookie cookie : cookies) {
        		if(cookie.getName().equals("userName")) {
        			loginCookie = cookie;
        		}
        		if(cookie.getName().equals("password")) {
        			passwordCookie = cookie;
        		}
        	}
        }
        
        if(loginCookie != null) {
        	//loginCookie.setMaxAge();
        	response.addCookie(loginCookie);
        	System.out.println("Found a cookie with value "+loginCookie.getValue());
        	model.addAttribute("userName", loginCookie.getValue());
        	if(passwordCookie!=null){
        		response.addCookie(passwordCookie);
            	System.out.println("Found a cookie with value "+passwordCookie.getValue());
            	model.addAttribute("password", passwordCookie.getValue());
        	}
        }
        else {
        	System.out.println("Found no cookie");
        }
        
        return "login";
    }
     
   
    @RequestMapping(value="/loginpage" ,method=RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute("user")UserAccount user, BindingResult result,Model model, @RequestParam(required=false)String remember,HttpServletRequest request, HttpServletResponse response){
    	 if(result.hasErrors()){
    		 System.out.println("errrooorrrr");
        	 return "login";
         }else{
			try{
				System.out.println("loginuser"+user.getUserName());
				UserAccount userAccount=employeeDao.queryEmployeeByNameAndPassword(user.getUserName(),user.getPassword());
				//String str = request.getParameter("remember");
			    if(remember!=null){
			    	System.out.println("I am about to set cookie");
			    	Cookie userName = new Cookie("userName", userAccount.getUserName());
			    	userName.setMaxAge(7*86400);
			    	response.addCookie(userName);
			    	Cookie password = new Cookie("password", userAccount.getPassword());
			    	password.setMaxAge(7*86400);
			    	response.addCookie(password);
			    }
			    System.out.println(userAccount.getUserName());
				if(userAccount.getRole().equalsIgnoreCase("admin")){
					HttpSession session = request.getSession();
				    session.setAttribute("userAccount", userAccount);
				    UserAccount userAccount1=new UserAccount();
				    model.addAttribute("userAccount1",userAccount1);
					return"adminhome1";
					
				}
				else if(userAccount.getRole().equalsIgnoreCase("doctor")){
					HttpSession session = request.getSession();
				    session.setAttribute("userAccount", userAccount);
				    ArrayList<Patient> pList=employeeDao.getpatientfordoctor(userAccount.getUserName());
				    ArrayList<LabWorkRequest> workRequestList=employeeDao.getLabWorkRequest();
				    model.addAttribute("pList", pList);
				    model.addAttribute("workRequestList",workRequestList);
					return"doctorworkarea";
				}
				else if(userAccount.getRole().equalsIgnoreCase("nurse")){
					HttpSession session = request.getSession();
				    session.setAttribute("userAccount", userAccount);
					return"nurseworkarea";
				}
				else if(userAccount.getRole().equalsIgnoreCase("labassistant")){
					HttpSession session = request.getSession();
				    session.setAttribute("userAccount", userAccount);
				    ArrayList<LabWorkRequest> wrList=employeeDao.getLabWorkRequest();
				    model.addAttribute("wrList",wrList);
					return"labassisworkarea";
				}
				else if(userAccount.getRole().equalsIgnoreCase("pharmacist")){
					HttpSession session = request.getSession();
				    session.setAttribute("userAccount", userAccount);
				    model.addAttribute("drug",new Drug());
					return"pharmacistworkarea";
				}
				else if(userAccount.getRole().equalsIgnoreCase("patient")){
					HttpSession session = request.getSession();
				    session.setAttribute("userAccount", userAccount);
				    model.addAttribute("userAccount",userAccount);
				    Patient p=(Patient)userAccount.getEmployee();
				    List<Encounter> encounterList=(List<Encounter>)p.getEncounterList();
				    model.addAttribute("encounterList",encounterList);
					return"patientworkarea";
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    	//	return "loginError";
		}
         
			
			return"loginerror";
         }
		
		
		
    }
    
    @RequestMapping(value = "/addemployee1", method = RequestMethod.GET)
    public String addemployee(Model model,HttpServletRequest request) {
    	
    	HttpSession session = request.getSession();
   //model.addAttribute("e", new UserAccount());
    	 model.addAttribute("userAccount1",new UserAccount());
        return "adminhome1";
    } 
    
    @RequestMapping(value= "/addemployee", method = RequestMethod.POST)
    public String addEmployee(@Valid @ModelAttribute("userAccount1")UserAccount userAccount1,BindingResult result,Model model, HttpServletRequest request){
         if(result.hasErrors()){
        	 return "adminhome1";
         }
         else{
            try {
            	//HttpSession session = request.getSession();
            	//user=new UserAccount();
            	Boolean isValid=employeeDao.employeeCheck(userAccount1);
            	if(!isValid){
            		return "admin1";
            	           	
            	}
            	employeeDao.addEmployee(userAccount1);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        return "succeesss";
         }
         
    }
  
    
    @RequestMapping(value= "/backhome", method = RequestMethod.POST)
    public String back(Model model, UserAccount user, BindingResult result, HttpServletRequest request){
         
    	HttpSession session = request.getSession();
	    
	    UserAccount userAccount1=new UserAccount();
	    model.addAttribute("userAccount1",userAccount1);
		return"adminhome1";
           
         
    }
  
    
    @RequestMapping(value="/showemployees",method = RequestMethod.POST)
	public String showEmployees(Model model,  HttpServletRequest request){
		//HttpSession session = request.getSession();
		
		ArrayList<Employee> employeeList = employeeDao.showEmployees();
		model.addAttribute("employeeList", employeeList);
		return "showemployees";
		
	}
    
   
    
   
    @RequestMapping(value="/deleteemployees")
	public String deleteMessages(Model model, @Validated Employee e, BindingResult result, HttpServletRequest request){
	//	System.out.println("Number of messages to be deleted is "+messageList.size());
		String[] empID = request.getParameterValues("deleteemployees");
		for(String str: empID)
			System.out.println("empID is "+str);
		
		 try {
			 employeeDao.deleteemployees(empID);
		 } catch (Exception e1) {
			 e1.printStackTrace();
		 }
				
		return "showemployees";
	}
    
   
   

	@RequestMapping(value="/logout")
	public String showLogout(Model model, HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie userName = new Cookie("userName", "pass");
		Cookie password = new Cookie("password", "pass");
		userName.setMaxAge(0);
		password.setMaxAge(0);
		response.addCookie(userName);
		response.addCookie(password);
		return "logouttt";
		
	}
	
	
}
