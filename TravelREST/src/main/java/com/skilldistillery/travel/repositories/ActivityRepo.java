package com.skilldistillery.travel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.travel.entities.Activity;

public interface ActivityRepo extends JpaRepository<Activity, Integer> {

//	List<Activity> findAllOrderByStartDateDesc();
	List<Activity> findByNameLikeOrDescriptionLike(String name, String description);
	List<Activity> findByOwner_FnameLikeOrOwner_LnameLike(String fname, String lname);
	List<Activity> findByTrip_NameLike(String keyword);
	List<Activity> findByCategories_Id(int catId);
	
}
