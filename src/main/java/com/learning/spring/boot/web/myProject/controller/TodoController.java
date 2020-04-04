package com.learning.spring.boot.web.myProject.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.learning.spring.boot.web.myProject.model.Todo;
import com.learning.spring.boot.web.myProject.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodosList(ModelMap model) {
		model.put("todos", todoService.retrieveTodos(getUserSession(model)));
		return "list-todos";
	}
	
	@RequestMapping(value = "/add-todos", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getUserSession(model), "", new Date(), false));
		return "todo";
	}
	
	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult ) {
		if (bindingResult.hasErrors())
			return "todo";
		todoService.addTodo(getUserSession(model), todo.getDesc(), todo.getTargetDate(), false );
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodoPage(ModelMap model, @RequestParam int id) {
		Todo todo = todoService.retrieveTodo(getUserSession(model), id);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "todo";
		 
		todo.setUser(getUserSession(model));
		todoService.updateTodo(todo);
		return "redirect:/list-todos";
	}
	
	private String getUserSession(ModelMap model) {
		return (String)model.get("name");
	}
}
