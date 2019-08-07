package com.cloudy9101.todolist.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cloudy9101.todolist.models.List;
import com.cloudy9101.todolist.models.ListRepository;
import com.cloudy9101.todolist.models.Todo;
import com.cloudy9101.todolist.models.TodoRepository;

@Controller
public class HomeController {
	@Autowired
	private ListRepository listRepository;
	@Autowired
	private TodoRepository todoRepository;
	
	@RequestMapping(value = "/home")
	public ModelAndView home(Model model) {
		Iterable<List> lists = listRepository.findAll();
		HashMap<Integer, Iterable<Todo>> listTodos = new HashMap<Integer, Iterable<Todo>>();
		lists.forEach(list -> {
			Iterable<Todo> todos = todoRepository.findByListId(list.getId());
			listTodos.put(list.getId(), todos);
		});
		
		ModelAndView mav = new ModelAndView();
	    mav.addObject("lists", lists);
	    mav.addObject("listTodos", listTodos);
	    mav.setViewName("home");

        return mav;
	}
}
