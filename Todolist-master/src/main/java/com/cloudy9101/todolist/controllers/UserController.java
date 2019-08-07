package com.cloudy9101.todolist.controllers;

import javax.jws.soap.SOAPBinding.Use;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloudy9101.todolist.models.User;
import com.cloudy9101.todolist.models.UserRepository;

import ch.qos.logback.core.net.LoginAuthenticator;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value= "/", method = RequestMethod.GET)
	public ModelAndView viewLogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView Login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");		
		return mav;
	}
	
	@RequestMapping(value= {"/registration"}, method = RequestMethod.GET)
	public ModelAndView registration(User user) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registration");
		return mav;
	}
	
	@RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		Iterable<User> users = userRepository.findAll();
		if(bindingResult.hasErrors()) {
			mav.setViewName("registration");
		} else {
			mav.setViewName("success");
		}
		return mav;
	}
	
}