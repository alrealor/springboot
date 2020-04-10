package com.alrealor.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alrealor.springboot.web.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
    @RequestMapping(value="/login", method=RequestMethod.GET)
    //@ResponseBody //Response into the response body
    public String showLoginPage(ModelMap model){    	
        return "login"; // go to login.jsp by Dispatcher Servlet
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String showWelcomePage(ModelMap model
    		                    , @RequestParam String user 
    		                    , @RequestParam String password){
    	
    	if(!loginService.isValidUser(user, password)) {
    		model.put("errorMessage", "Invalid Credentials!");
    		return "login";    		
    	}
    	
    	model.put("user", user);
    	model.put("password", password);
        return "welcome"; 
    }    
}
