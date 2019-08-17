package com.cloudy9101.todolist.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cloudy9101.todolist.models.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;
		
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void whenViewLogin_thenReturnModelAndView() throws Exception {
	    mvc.perform(get("/"))
	      .andExpect(status().isOk())
	      .andExpect(view().name("login"));
	}
}
