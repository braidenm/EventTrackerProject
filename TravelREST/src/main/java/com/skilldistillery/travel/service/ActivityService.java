package com.skilldistillery.travel.service;

import java.util.List;

import com.skilldistillery.travel.entities.Activity;

public interface ActivityService {
	
	List<Activity> getAll();
	Activity getById(int id);
	Activity replace(int id, Activity act);
	Activity create(Activity act);
	void delete(Activity act);

}
