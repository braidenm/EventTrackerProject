package com.skilldistillery.travel.service;

import java.util.List;

import com.skilldistillery.travel.entities.Trip;

public interface TripService {
	
	List<Trip> getAll();
	Trip getById(int id);
	Trip replace(int id, Trip trip);
	Trip create(Trip trip);
	void delete(Trip trip);

}
