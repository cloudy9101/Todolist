package com.cloudy9101.todolist.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.cloudy9101.todolist.models.List;
import com.cloudy9101.todolist.models.ListRepository;
import com.cloudy9101.todolist.models.Todo;
import com.cloudy9101.todolist.models.TodoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(TodosController.class)
public class TodosControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TodoRepository todoRepository;

	@Test
	public void givenList_whenCreateTodo_thenReturnModelAndView()
	  throws Exception {
		List list = new List();
		list.setName("List");
		list.setId(1);

	    Todo todo = new Todo();
	    todo.setListId(list.getId());
	    todo.setName("Todo");
	    
	    mvc.perform(post("/lists/" + list.getId() + "/todos").contentType(MediaType.APPLICATION_JSON_UTF8).params(convert(todo)))
	      .andExpect(status().isOk())
	      .andExpect(view().name("todo"));
	}

	@Test
	public void givenTodo_whenDeleteTodo_thenReturnView() throws Exception {	     
	    Todo todo = new Todo();
	    todo.setListId(1);
	    todo.setName("Todo");
	    todo.setId(1);
	    
	    Mockito.doNothing().when(todoRepository).deleteById(todo.getId());
	    	    
	    mvc.perform(delete("/todos/" + todo.getId()))
	    	.andExpect(status().isOk());
	}
	
    @Autowired
    private ObjectMapper objectMapper;
     
    private MultiValueMap<String, String> convert(Object obj) {
        MultiValueMap parameters = new LinkedMultiValueMap<String, String>();
        Map<String, String> maps = objectMapper.convertValue(obj, new TypeReference<Map<String, String>>() {});
        parameters.setAll(maps);
     
        return parameters;
    }
}
