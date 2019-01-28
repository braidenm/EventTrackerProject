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
	
	@GetMapping("users/search/name/{kword}")
	public List<User> searchByName(@PathVariable String kword, HttpServletResponse res){
		
		
		List<User> tripList = userService.getAllByName(kword);
		if(!tripList.isEmpty()) {
			res.setStatus(201);
			return tripList;
		}
		res.setStatus(404);
		return null;
	}
	@GetMapping("users/search/username/{kword}")
	public List<User> searchByUserName(@PathVariable String kword, HttpServletResponse res){
		
		
		List<User> tripList = userService.getAllUsername(kword);
		if(!tripList.isEmpty()) {
			res.setStatus(201);
			return tripList;
		}
		res.setStatus(404);
		return null;
	}
	@GetMapping("users/search/roll/{kword}")
	public List<User> searchByRoll(@PathVariable String kword, HttpServletResponse res){
		
		
		List<User> tripList = userService.getAllByRoll(kword);
		if(!tripList.isEmpty()) {
			res.setStatus(201);
			return tripList;
		}
		res.setStatus(404);
		return null;
	}
	@GetMapping("users/search/activity/{kword}")
	public List<User> searchByActivity(@PathVariable String kword, HttpServletResponse res){
		
		
		List<User> tripList = userService.getAllByActivities(kword);
		if(!tripList.isEmpty()) {
			res.setStatus(201);
			return tripList;
		}
		res.setStatus(404);
		return null;
	}
	@GetMapping("users/search/active/{bool}")
	public List<User> searchByUserActive(@PathVariable Boolean bool, HttpServletResponse res){
		
		
		List<User> tripList = userService.getAllByActive(bool);
		if(!tripList.isEmpty()) {
			res.setStatus(201);
			return tripList;
		}
		res.setStatus(404);
		return null;
	}
	@GetMapping("users/search/banned/{bool}")
	public List<User> searchByUserBanned(@PathVariable Boolean bool, HttpServletResponse res){
		
		
		List<User> tripList = userService.getAllBybanned(bool);
		if(!tripList.isEmpty()) {
			res.setStatus(201);
			return tripList;
		}
		res.setStatus(404);
		return null;
	}
	@PutMapping("users/{userId}/add/trips/{tripId}")
	public User addTripToUser(@PathVariable Integer userId, @PathVariable Integer tripId, HttpServletResponse res){
		
		return userService.addTrip(tripId, userId);
	}
	@PutMapping("users/{userId}/remove/trips/{tripId}")
	public User removeTripfromUser(@PathVariable Integer userId, @PathVariable Integer tripId, HttpServletResponse res){
		
		return userService.removeTrip(tripId, userId);
	}
	@PutMapping("users/{userId}/add/activities/{actId}")
	public User addActivityToUser(@PathVariable Integer userId, @PathVariable Integer actId, HttpServletResponse res){
		
		return userService.addActivity(actId, userId);
	}
	@PutMapping("users/{userId}/remove/activities/{tripId}")
	public User removeActivityfromUser(@PathVariable Integer userId, @PathVariable Integer actId, HttpServletResponse res){
		
		return userService.removeTrip(actId, userId);
	}
	
	
	

}
