package com.cloudy9101.todolist.models;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
}
