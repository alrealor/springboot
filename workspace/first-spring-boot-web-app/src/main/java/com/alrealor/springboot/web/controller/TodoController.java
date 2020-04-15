package com.alrealor.springboot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alrealor.springboot.web.model.Todo;
import com.alrealor.springboot.web.service.ActiveNavigationItemService;
import com.alrealor.springboot.web.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	ActiveNavigationItemService activeNavService;

	/* Better use @DateTimeFormat(pattern = "dd/MM/yyyy") in Todo bean
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	  binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException{
	      setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	    }
	    @Override
	    public String getAsText() throws IllegalArgumentException {
	      return DateTimeFormatter.ofPattern("dd/MM/yyyy").format((LocalDate) getValue());
	    }
	  });
	}*/
	
		
	/** 
	 * Show list of Todos controller
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/list-todos", method=RequestMethod.GET)
    public String showTodos(ModelMap model){ 
    	
    	String user = this.getLoggedInUserName();
    	
    	List <Todo> todos = todoService.retrieveTodos(user);
    	
    	model.put("todos", todos);
    	activeNavService.setActiveNavigationItem(model, "list-todos");
    	
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
    	
    	String user = this.getLoggedInUserName();
    	
    	//Add todo using todo service
    	todoService.addTodo(user, todo.getDescription(), todo.getTargetDate(), false);
    	
    	// Redirect to list-todo page
        return "redirect:/list-todos";
    }
        
    /**
     *  Delete todo controller logic
     * @param id
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/delete-todo", method=RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) throws Exception{   
    	if(id == 1) {
    		throw new Exception("Dummy Custom Exception");
    	}
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

    	todo.setUser(this.getLoggedInUserName());
    	
    	// Update Todo from service
    	todoService.updateTodo(todo);

        return "redirect:/list-todos";
    }
    
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}    
       
}
