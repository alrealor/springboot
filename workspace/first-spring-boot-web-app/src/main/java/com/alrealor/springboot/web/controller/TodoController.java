package com.alrealor.springboot.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alrealor.springboot.web.model.Todo;
import com.alrealor.springboot.web.service.TodoService;

@Controller
@SessionAttributes("user")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
    @RequestMapping(value="/list-todos", method=RequestMethod.GET)
    public String showTodos(ModelMap model){ 
    	String user = (String) model.get("user");
    	System.out.println(user);
    	List <Todo> todos = todoService.retrieveTodos(user);
    	model.put("todos", todos);
    	
        return "list-todos";
    }
    
    @RequestMapping(value="/add-todo", method=RequestMethod.GET)
    public String showAddTodo(ModelMap model){     	
        return "todo";
    }
        
    @RequestMapping(value="/add-todo", method=RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String description){
    	String user = model.get("user").toString();
    	todoService.addTodo(user, description, LocalDate.now(), false);
        return "redirect:/list-todos";
    }
    
    
    @RequestMapping(value="/delete-todo", method=RequestMethod.GET)
    public String deleteTodo(@RequestParam int id){    	
    	todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }      
    
    
}
