package com.skilldistillery.travel.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.travel.entities.Activity;
import com.skilldistillery.travel.entities.Trip;
import com.skilldistillery.travel.entities.User;
import com.skilldistillery.travel.repositories.TripRepo;
import com.skilldistillery.travel.repositories.UserRepo;
@Service
@Transactional
public class TripServiceImpl implements TripService {
	
	@Autowired
	private TripRepo repo;
	@Autowired
	private UserRepo uRepo;
	@Autowired
	private ActivityService actServ;
	
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
			if (trip.getOwner().getId() > 0) {
				User owner = uRepo.findById(trip.getOwner()
											.getId()).get();
				managed.setOwner(owner);
			}
			return managed;
			
		}
		return null;
	}

	@Override
	public Trip create(Trip trip) {

		try {
			if (trip.getOwner().getId() > 0) {
				Optional<User> owner = uRepo.findById(trip.getOwner()
											.getId());
				
				if (owner.isPresent()) {
					
					trip.setOwner(owner.get());
				}
				else {
					throw new Exception("Not a vaild User");
				}
			}
			else {
				throw new Exception("Not a vaild User");
			}
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
		if(tripOpt.isPresent()) {
			
			try {
				trip = tripOpt.get();
				List<Activity> actList = trip.getActivities();
				for (Activity activity : actList) {
					trip.removeActivity(activity);
					actServ.delete(activity);
				}
				trip.setOwner(null);
				for(User user: trip.getUsers()) {
					trip.removeUser(user);
				}
				repo.delete(trip);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Trip> getByNameOrDescription(String keyword) {
		String[] kwordArr = keyword.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		
		return repo.findByNameLikeOrDescriptionLike(kwordBuild.toString(), kwordBuild.toString());
	}

	@Override
	public List<Trip> getByOwnerName(String keyword) {

		String[] kwordArr = keyword.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		return repo.findByOwner_FnameLikeOrOwner_LnameLike(kwordBuild.toString(), kwordBuild.toString());
	}

	@Override
	public List<Trip> getByUsersName(String keyword) {

		String[] kwordArr = keyword.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		return repo.findByUsers_FnameLikeOrUsers_LnameLike(kwordBuild.toString(), kwordBuild.toString());
	}

	@Override
	public List<Trip> getByActivities(String keyword) {

		String[] kwordArr = keyword.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		return repo.findByActivities_NameLikeOrActivities_descriptionLike(kwordBuild.toString(), kwordBuild.toString());
	}

}
