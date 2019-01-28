package com.skilldistillery.travel.service;

import java.util.List;

import com.skilldistillery.travel.entities.Activity;

public interface ActivityService {
	
	List<Activity> getAll();
//	List<Activity> getAllSortByStartDateDesc();
	List<Activity> getByNameOrDescription(String keyword);
	List<Activity> getByOwnerName(String keyword);
	List<Activity> getByTripName(String keyword);
	List<Activity> getByCategoryId(int catId);
	Activity getById(int id);
	Activity replace(int id, Activity act);
	Activity create(Activity act);
	void delete(Activity act);
	
	

}
