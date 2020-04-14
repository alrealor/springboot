package com.alrealor.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alrealor.springboot.web.service.ActiveNavigationItemService;

@Controller
public class WelcomeController {
	
	@Autowired
	ActiveNavigationItemService activeNavService;
	
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showWelcomePage(ModelMap model){ 
    	
    	model.put("user", this.getLoggedInUserName());
    	
    	activeNavService.setActiveNavigationItem(model, "welcome");
    	
    	return "welcome"; // go to login.jsp by Dispatcher Servlet       
    }

    /**
     * Get user from spring security
     * @return
     */
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		
		return principal.toString();
	} 
}
