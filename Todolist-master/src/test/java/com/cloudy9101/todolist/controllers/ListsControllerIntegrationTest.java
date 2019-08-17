package com.cloudy9101.todolist.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.cloudy9101.todolist.models.List;
import com.cloudy9101.todolist.models.ListRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ListsController.class)
public class ListsControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ListRepository listRepository;

	@Test
	public void givenList_whenCreateList_thenReturnModelAndView()
	  throws Exception {
		List list = new List();
		list.setName("List");
		list.setId(1);
	    
	    mvc.perform(post("/lists").contentType(MediaType.APPLICATION_JSON_UTF8).params(convert(list)))
	      .andExpect(status().isOk())
	      .andExpect(view().name("listCard"));
	}

	@Test
	public void givenList_whenDeleteList_thenReturnView() throws Exception {	     
		List list = new List();
		list.setName("List");
		list.setId(1);
	    
	    Mockito.doNothing().when(listRepository).deleteById(list.getId());
	    	    
	    mvc.perform(delete("/lists/" + list.getId()))
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
