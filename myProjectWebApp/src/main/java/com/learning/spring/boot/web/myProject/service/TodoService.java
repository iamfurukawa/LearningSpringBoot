package com.learning.spring.boot.web.myProject.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.spring.boot.web.myProject.model.Todo;


@Service
public class TodoService {
	private List<Todo> todos = new ArrayList<>();
	private int todoCount = 0;

	public TodoService() {
		super();
		todos.add(new Todo(1, "admin", "Learn Spring MVC", new Date(), false));
		todos.add(new Todo(2, "admin", "Learn Struts", new Date(), false));
		todos.add(new Todo(3, "admin", "Learn Hibernate", new Date(), false));
		todos.add(new Todo(0, "otherUser", "Learn Hibernate3", new Date(), false));
		todoCount = todos.size();
	}

	public List<Todo> retrieveTodos(String user) {
		List<Todo> filteredTodos = new ArrayList<>();
		for (Todo todo : todos) {
			if (todo.getUser().equals(user)) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}
	
	public Todo retrieveTodo(String user, int id) {
		for (Todo todo : todos) {
			if (todo.getUser().equals(user) && todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}

	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
	}
	
	public void updateTodo(Todo todo) {
		 todos.remove(todo);
		 todos.add(todo);
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
}
