package com.cloudy9101.todolist.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class List {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	public Integer getId() {
		return id;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
