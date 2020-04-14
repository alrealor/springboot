package com.alrealor.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
		
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		binder.registerCustomEditor(LocalDate.class,)
	}
	*/
	
	
	// List Todos controller
    @RequestMapping(value="/list-todos", method=RequestMethod.GET)
    public String showTodos(ModelMap model){ 
    	
    	String user = (String) model.get("user");
    	
    	List <Todo> todos = todoService.retrieveTodos(user);
    	
    	model.put("todos", todos);
    	
        return "list-todos";
    }
        
	/**
	 *  Show Add Todo Page
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/add-todo", method=RequestMethod.GET)
    public String showAddTodoPage(ModelMap model){ 
    	
    	// this Todo object will be binded to modelAttribute="todo" in JSP
    	// .put or .addAttribute methods add to the model
    	model.addAttribute("todo", new Todo(1, model.get("user").toString(), "", null, false));
    	
        return "todo";
    }
        
    /**
     * Add Todo Controller Logic 
     * @param model
     * @param todo
     * @param results
     * @return
     */
    @RequestMapping(value="/add-todo", method=RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult results){
    	
    	if (results.hasErrors()) {
    		return "todo";
    	}
    	
    	String user = model.get("user").toString();
    	
    	//Add todo using todo service
    	todoService.addTodo(user, todo.getDescription(), todo.getTargetDate(), false);
    	
    	// Redirect to list-todo page
        return "redirect:/list-todos";
    }
        
    /**
     *  Delete todo controller logic
     * @param id
     * @return
     */
    @RequestMapping(value="/delete-todo", method=RequestMethod.GET)
    public String deleteTodo(@RequestParam int id){    	
    	todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }
    
    /**
     * Show Update Todo Page
     * @param id
     * @return
     */
    @RequestMapping(value="/update-todo", method=RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model){    	
    	Todo todo = todoService.getTodoById(id);
		if (todo != null) {
			model.put("todo", todo);
		}
        return "todo";
    }
    
	/**
	 * Update Todo Controller logic
	 * @param model
	 * @param todo
	 * @return
	 */
    @RequestMapping(value="/update-todo", method=RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult results){ 
    	
    	if (results.hasErrors()) {
    		return "todo";
    	}

    	todo.setUser(model.get("user").toString());
    	
    	// Update Todo from service
    	todoService.updateTodo(todo);

        return "redirect:/list-todos";
    }      
    
    
}
