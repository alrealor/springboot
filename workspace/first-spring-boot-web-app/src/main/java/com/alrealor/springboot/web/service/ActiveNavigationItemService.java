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
    				model.put("activeNavItem", "homeItem");
    				break;
    		case "list-todos": 
    				model.put("activeNavItem", "todosItem"); 
    				break;
    		case "welcome": 
				model.put("activeNavItem", "homeItem"); 
				break;    				
    		default: 
    				model.put("activeNavItem", "homeItem");
    	}
    }

}
