package com.skilldistillery.travel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.travel.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	List<User> findByFnameLikeOrLnameLike(String fname, String lname);
	List<User> findByUsername(String keyword);
	List<User> findByActive(Boolean active);
	List<User> findByBanned(Boolean banned);
	List<User> findByRole(String role);
	List<User> findByActivities_nameLikeOrActivities_Description(String activityName, String description);
	
	
}
