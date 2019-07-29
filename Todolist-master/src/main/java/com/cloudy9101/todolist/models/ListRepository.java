package com.cloudy9101.todolist.models;

import org.springframework.data.repository.CrudRepository;


public interface ListRepository extends CrudRepository<List, Integer> {
	
}