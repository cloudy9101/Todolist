package com.cloudy9101.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloudy9101.todolist.models.List;
import com.cloudy9101.todolist.models.ListRepository;

@Controller
public class ListsController {
	@Autowired
	private ListRepository listRepository;
	
	@PostMapping(path="/lists")
	public @ResponseBody ModelAndView createList (@RequestParam String name) {
		List list = new List();
		list.setName(name);
		listRepository.save(list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("listCard");
		return mav;
	}
	
	@DeleteMapping(path="/lists/{id}")
	public @ResponseBody String deleteList (@PathVariable Integer id) {
		listRepository.deleteById(id);
		return "Deleted";
	}
}
