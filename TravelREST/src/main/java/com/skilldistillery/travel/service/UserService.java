package com.skilldistillery.travel.service;

import java.util.List;

import com.skilldistillery.travel.entities.User;

public interface UserService {
	
	List<User> getAll();
	User getById(int id);
	User replace(int id, User user);
	User create(User user);
	void delete(User user);

}
