package com.cloudy9101.todolist.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloudy9101.todolist.models.Todo;
import com.cloudy9101.todolist.models.TodoRepository;

@Controller
public class TodosController {
	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping(path="lists/{listId}/todos")
	public @ResponseBody ModelAndView createTodo (@PathVariable Integer listId, @RequestParam String name, @CookieValue(value = "userId", defaultValue = "0") String userId) {
		Todo todo = new Todo();
		todo.setListId(listId);
		todo.setName(name);
		todo.setCreatorId(Integer.parseInt(userId));
		
		todoRepository.save(todo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("todo", todo);
		mav.setViewName("todo");
		
		return mav;
	}
	
	@PatchMapping(path="/todos/{id}")
	public @ResponseBody String updateTodo(@PathVariable Integer id,
			@RequestParam String name,
			@RequestParam String description,
			@RequestParam Integer assigneeId) {
		Optional<Todo> todo = todoRepository.findById(id);
		if(todo != null) {
			Todo t = todo.get();
			if(name != "") { t.setName(name); }
			if(description != "") { t.setDescription(description); }
			if(assigneeId != null) { t.setAssigneeId(assigneeId); }
		}
		return "Updated";
	}
	
	@DeleteMapping(path="/todos/{id}")
	public @ResponseBody String deleteList (@PathVariable Integer id) {
		todoRepository.deleteById(id);
		return "Deleted";
	}
}
