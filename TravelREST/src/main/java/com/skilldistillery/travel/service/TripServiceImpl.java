package com.skilldistillery.travel.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.travel.entities.Trip;
import com.skilldistillery.travel.repositories.TripRepo;
@Service
@Transactional
public class TripServiceImpl implements TripService {
	
	@Autowired
	private TripRepo repo;
	
	@Override
	public List<Trip> getAll() {
		return repo.findAll();
	}

	@Override
	public Trip getById(int id) {
		
		Optional<Trip> tripOpt = repo.findById(id);
		if(tripOpt.isPresent()) {
			return tripOpt.get();
			
		}
		return null;
	}

	@Override
	public Trip replace(int id, Trip trip) {

		Optional<Trip> tripOpt = repo.findById(id);
		
		if(tripOpt.isPresent()) {
			Trip managed = tripOpt.get();
			managed.setName(trip.getName());
			managed.setDescription(trip.getDescription());
			managed.setOwner(trip.getOwner());
			return managed;
			
		}
		return null;
	}

	@Override
	public Trip create(Trip trip) {

		try {
			repo.saveAndFlush(trip);
			return trip;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(Trip trip) {
		
		Optional<Trip> tripOpt = repo.findById(trip.getId());
		if(tripOpt.isPresent() && tripOpt.get().getActivities().isEmpty() && tripOpt.get().getUsers().isEmpty()) {
			
			try {
				repo.delete(trip);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
