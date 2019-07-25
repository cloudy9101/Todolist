package com.cloudy9101.todolist.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value = "/home")
	public ModelAndView home() {
		HashMap<String, String> params = new HashMap<String, String>();
        params.put("title", "Hello");

        return new ModelAndView("home", params);
	}
}
