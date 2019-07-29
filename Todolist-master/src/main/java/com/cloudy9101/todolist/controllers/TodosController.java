package com.cloudy9101.todolist.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudy9101.todolist.models.Todo;
import com.cloudy9101.todolist.models.TodoRepository;

@Controller
public class TodosController {
	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping(path="/todos")
	public @ResponseBody String createTodo (@RequestParam String name) {
		Todo todo = new Todo();
		todo.setName(name);
		todoRepository.save(todo);
		return "Saved";
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
