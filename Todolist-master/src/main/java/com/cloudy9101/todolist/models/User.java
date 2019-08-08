package com.cloudy9101.todolist.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@NotEmpty(message = "Please provide your first name")
	private String name;
	
	@NotEmpty(message = "Please provide a valid email address")
	@Email(message = "Invalid email, Please provide a valid email address")
	private String email;
	
	@NotEmpty(message = "Please provide a valid password")
	private String password;
	
	public int _getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
