package com.alrealor.springboot.web.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class ActiveNavigationItemService {
	
    /**
     * Set active navigatio item to put focus on proper menu
     * @param model
     * @param pageSource
     */
    public void setActiveNavigationItem(ModelMap model, String pageSource) {
    	
    	switch(pageSource) {
    		case "login": 
    				model.put("activeNavItem", "homeMenuItem");
    				break;
    		case "list-todos": 
    				model.put("activeNavItem", "todosMenuItem"); 
    				break;
    		case "welcome": 
				model.put("activeNavItem", "homeMenuItem"); 
				break;    				
    		default: 
    				model.put("activeNavItem", "homeMenuItem");
    	}
    }

}
