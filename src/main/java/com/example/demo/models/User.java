package com.example.demo.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
public class User {
	@Value("${user.id}")//ESTO ES EXCLUSIVO PARA QUE SE VEA EN HTML
	private int id;
	@Value("${user.dni}")
	private int dni;
	@Value("${user.name}")
	private String name;
	@Value("${user.lastName}")
	private String lastName;
	@Value("${user.sex}")
	private int sex;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
}
