package com.skilldistillery.travel.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.travel.entities.User;
import com.skilldistillery.travel.repositories.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public List<User> getAll() {
		return repo.findAll();
	}

	@Override
	public User getById(int id) {

		Optional<User> userOpt = repo.findById(id);
		if(userOpt.isPresent()) {
			return userOpt.get();
			
		}
		return null;
	}

	@Override
	public User replace(int id, User user) {

		Optional<User> userOpt = repo.findById(id);
		
		if(userOpt.isPresent()) {
			User managed = userOpt.get();
			managed.setLname(user.getLname());
			managed.setFname(user.getFname());
			managed.setUsername(user.getUsername());
			managed.setPassword(user.getPassword());
			managed.setActive(user.isActive());
			managed.setBanned(user.isBanned());
			managed.setRole(user.getRole());
			managed.setAddress(user.getAddress());
			return managed;
			
		}
		
		return null;
	}

	@Override
	public User create(User user) {

		try {
			repo.saveAndFlush(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public void delete(User user) {

		Optional<User> userOpt = repo.findById(user.getId());
		if(userOpt.isPresent()) {
			
			try {
				repo.delete(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
