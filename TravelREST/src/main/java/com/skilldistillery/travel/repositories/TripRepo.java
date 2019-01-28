package com.skilldistillery.travel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.travel.entities.Trip;

public interface TripRepo extends JpaRepository<Trip, Integer> {
	
	List<Trip> findByNameLikeOrDescriptionLike(String name, String description);
	List<Trip> findByOwner_FnameLikeOrOwner_LnameLike(String fname, String lname);
	List<Trip> findByUsers_FnameLikeOrUsers_LnameLike(String fname, String lname);
	List<Trip> findByActivities_NameLikeOrActivities_descriptionLike(String name, String description);
	
	
}
