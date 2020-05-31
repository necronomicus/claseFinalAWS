package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.User;
import com.example.demo.services.SUsuarios;



@Controller
public class CUser {

	@Autowired //no lo crees, traeme el que esta creado por ser Service. 
	SUsuarios x;
	
	
	@GetMapping(value = "/Usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getAll()
	{
		
		return x.getAll();
	}
	
	
	
	
	@GetMapping(value = "/Usuario/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getUser(@PathVariable int id)
	{
		
		 return x.getUser(id);
	}
}
