package com.skilldistillery.travel.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.travel.entities.Activity;
import com.skilldistillery.travel.entities.Address;
import com.skilldistillery.travel.entities.Trip;
import com.skilldistillery.travel.entities.User;
import com.skilldistillery.travel.repositories.AddressRepo;
import com.skilldistillery.travel.repositories.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;
	@Autowired
	private AddressRepo aRepo;
	@Autowired
	private TripService tServe;
	@Autowired
	private ActivityService aServe;
	
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
			aRepo.saveAndFlush(user.getAddress());
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
				user = userOpt.get();
				for(Activity act : user.getActivities()) {
					user.removeActivity(act);
				}
				for(Activity act : user.getOwnedActivities()) {
					user.removeOwnedActivity(act);
					aServe.delete(act);
				}
				user.setAddress(null);
				for(Trip trip : user.getTrips()) {
					user.removeTrip(trip);
				}
				for(Trip trip : user.getOwnedTrips()) {
					
					user.removeOwnedTrip(trip);
					tServe.delete(trip);
				}
				Address add = aRepo.findById(user.getAddress().getId()).get();
				user.setAddress(null);
				aRepo.delete(add);
				
				repo.delete(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<User> getAllByName(String fname) {
		String[] kwordArr = fname.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		
		return repo.findByFnameLikeOrLnameLike(kwordBuild.toString(), kwordBuild.toString());
	}

	@Override
	public List<User> getAllUsername(String keyword) {

		String[] kwordArr = keyword.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		return repo.findByUsername(kwordBuild.toString());
	}

	@Override
	public List<User> getAllByActive(boolean active) {

		return repo.findByActive(active);
	}

	@Override
	public List<User> getAllBybanned(boolean banned) {
		return repo.findByBanned(banned);
	}

	@Override
	public List<User> getAllByRoll(String role) {
		return repo.findByRole(role);
	}

	@Override
	public List<User> getAllByActivities(String activityName) {
		String[] kwordArr = activityName.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		return repo.findByActivities_nameLikeOrActivities_Description(kwordBuild.toString(), kwordBuild.toString());
	}

}
