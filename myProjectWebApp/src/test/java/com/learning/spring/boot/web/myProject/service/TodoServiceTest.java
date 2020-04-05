package com.learning.spring.boot.web.myProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.learning.spring.boot.web.myProject.model.Todo;

class TodoServiceTest {
	
	private static final String DUMMY = "Dummy";
	private static final int ANY_INT = 1000;
	private static final int ID_0 = 0;
	private static final int ID_1 = 1;
	private static final int ID_2 = 2;
	private static final int ID_3 = 3;
	private static final int ID_5 = 5;
	private static final String OTHER_USER = "otherUser";
	private static final String ADMIN = "admin";
	private static final String LEARN_SPRING_MVC = "Learn Spring MVC";
	private static final String LEARN_STRUTS = "Learn Struts";
	private static final String LEARN_HIBERNATE = "Learn Hibernate";
	private static final String LEARN_HIBERNATE3 = "Learn Hibernate3";
	
	private TodoService subject;
	
	@BeforeEach
	public void initialize() {
		subject = new TodoService();
	}
	
	@Test
	public void retrieveTodos_shouldNotRetriveTodosForUserEspecific() {
		List<Todo> todos = subject.retrieveTodos(DUMMY);
		assertEquals(0, todos.size());
	}

	@Test
	public void retrieveTodos_shouldRetriveTodosForUserEspecific() {
		List<Todo> todos = subject.retrieveTodos(ADMIN);
		assertEquals(3, todos.size());
		assertEquals(LEARN_SPRING_MVC, todos.get(0).getDesc());
		assertEquals(LEARN_STRUTS, todos.get(1).getDesc());
		assertEquals(LEARN_HIBERNATE, todos.get(2).getDesc());
		
		todos = subject.retrieveTodos(OTHER_USER);
		assertEquals(1, todos.size());
		assertEquals(LEARN_HIBERNATE3, todos.get(0).getDesc());
	}
	
	@Test
	public void retrieveTodo_shouldNotRetrieveTodoForUserAndId() {
		Todo todo = subject.retrieveTodo(DUMMY, ANY_INT);
		assertNull(todo);
	}
	
	@Test
	public void retrieveTodo_shouldRetrieveTodoForUserAndId() {
		Todo todo = subject.retrieveTodo(ADMIN, ID_2);
		assertEquals(LEARN_STRUTS, todo.getDesc());
		
		todo = subject.retrieveTodo(OTHER_USER, ID_0);
		assertEquals(LEARN_HIBERNATE3, todo.getDesc());
	}
	
	@Test
	public void deleteTodo_shouldDeleteTodos() {		
		subject.deleteTodo(ID_1);
		assertNull(subject.retrieveTodo(ADMIN, ID_1));
		
		subject.deleteTodo(ID_2);
		assertNull(subject.retrieveTodo(ADMIN, ID_2));
		
		subject.deleteTodo(ID_3);
		assertNull(subject.retrieveTodo(ADMIN, ID_3));
		
		subject.deleteTodo(ID_0);
		assertNull(subject.retrieveTodo(OTHER_USER, ID_0));
	}
	
	@Test
	public void addTodo_shouldAddTodos() {		
		List<Todo> todos = subject.retrieveTodos(DUMMY);
		assertEquals(0, todos.size());
		
		subject.addTodo(DUMMY, LEARN_HIBERNATE3, new Date(), false);
		todos = subject.retrieveTodos(DUMMY);
		assertEquals(1, todos.size());
		assertEquals(LEARN_HIBERNATE3, todos.get(0).getDesc());
		
		subject.addTodo(DUMMY, LEARN_SPRING_MVC, new Date(), false);
		todos = subject.retrieveTodos(DUMMY);
		assertEquals(2, todos.size());
		assertEquals(LEARN_SPRING_MVC, todos.get(1).getDesc());
	}
	
	@Test
	public void updateTodo_shouldUpdateTodo() {		
		List<Todo> todos = subject.retrieveTodos(DUMMY);
		assertEquals(0, todos.size());
		
		subject.addTodo(DUMMY, LEARN_HIBERNATE3, new Date(), false);
		todos = subject.retrieveTodos(DUMMY);
		assertEquals(1, todos.size());
		assertEquals(ID_5, todos.get(0).getId());
		assertEquals(LEARN_HIBERNATE3, todos.get(0).getDesc());
		
		subject.updateTodo(new Todo(ID_5, DUMMY, LEARN_SPRING_MVC, new Date(), false));
		todos = subject.retrieveTodos(DUMMY);
		assertEquals(1, todos.size());
		assertEquals(LEARN_SPRING_MVC, todos.get(0).getDesc());
	}
		

}
