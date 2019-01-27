package com.skilldistillery.travel.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.travel.entities.User;
import com.skilldistillery.travel.service.UserService;

@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public List<User> getAllUser(){
		return userService.getAll();
	}
	
	@GetMapping("users/{id}")
	public User getUserById(@PathVariable Integer id, HttpServletResponse response) {
		if(userService.getById(id) != null) {
			response.setStatus(201);
			
			return userService.getById(id);
		}
		
		response.setStatus(404);
		return null;
	}
	
	@PostMapping("users")
	public User createNewUser(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
		try {
			user = userService.create(user);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/");
			url.append(user.getId());
			response.addHeader("Location", url.toString());
		} catch (Exception e) {
			response.setStatus(400);
			user=null;
		}
		
		
		return user;
	}
	
	@PutMapping("users/{id}")
	public User replaceUser(@PathVariable Integer id, @RequestBody User user, HttpServletResponse res) {
		user = userService.replace(id, user);
		if (user != null) {
			res.setStatus(201);;
			return user;
		}
		res.setStatus(404);
		return null;
	}
	
	
	@DeleteMapping("users/{id}")
	public boolean deleteUser(@PathVariable Integer id, HttpServletResponse res) {
	
		try {
			
			userService.delete(userService.getById(id));;
			res.setStatus(201);
		} catch (Exception e) {
			res.setStatus(404);
			return false;
		}
			
		
		return true;
	}
	

}
