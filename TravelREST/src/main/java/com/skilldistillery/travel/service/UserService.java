package com.skilldistillery.travel.service;

import java.util.List;

import com.skilldistillery.travel.entities.User;

public interface UserService {
	
	List<User> getAll();
	List<User> getAllByName(String fname);
	List<User> getAllUsername(String keyword);
	List<User> getAllByActive(boolean active);
	List<User> getAllBybanned(boolean banned);
	List<User> getAllByRoll(String role);
	List<User> getAllByActivities(String activityName);
	User getById(int id);
	User replace(int id, User user);
	User create(User user);
	void delete(User user);

}
