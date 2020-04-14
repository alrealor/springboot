package com.alrealor.springboot.web.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alrealor.springboot.web.model.Todo;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    static {
        todos.add(new Todo(1, "usuario", "Learn Spring MVC", LocalDate.now(),false));
        todos.add(new Todo(2, "usuario", "Learn Struts", LocalDate.now(), false));
        todos.add(new Todo(3, "usuario", "Learn Hibernate", LocalDate.now(),false));
    }
    
    /**
     * Retrieve Todo's service
     * @param user
     * @return
     */
    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
            	
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    /**
     * Add Todo´s
     * @param name
     * @param desc
     * @param targetDate
     * @param isDone
     */
    public void addTodo(String name, String desc, LocalDate targetDate, boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
    
    /**
     * Get Todo by Id
     * @param Todo id
     * @return
     */
    public Todo getTodoById(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }
    
    /**
     * Update todo
     * @param Todo
     * @return
     */
    public void updateTodo(Todo todo) {
        if(null != todo) {
        	todos.remove(todo);
        	todos.add(todo);
        }
    }
    
}
