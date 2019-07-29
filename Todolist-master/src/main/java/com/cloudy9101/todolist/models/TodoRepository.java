package com.cloudy9101.todolist.models;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
	public ArrayList<Todo> findByListId(Integer listId);
}
