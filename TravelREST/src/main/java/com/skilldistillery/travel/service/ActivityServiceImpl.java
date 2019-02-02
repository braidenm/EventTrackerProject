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
import com.skilldistillery.travel.repositories.ActivityRepo;
import com.skilldistillery.travel.repositories.AddressRepo;
import com.skilldistillery.travel.repositories.TripRepo;
import com.skilldistillery.travel.repositories.UserRepo;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepo repo;
	@Autowired
	private TripRepo tRepo;
	@Autowired
	private UserRepo uRepo;
	@Autowired
	private AddressRepo aRepo;

	@Override
	public List<Activity> getAll() {
		return repo.findAll();
	}

	@Override
	public Activity getById(int id) {
		Optional<Activity> actOpt = repo.findById(id);
		if(actOpt.isPresent()) {
			return actOpt.get();
			
		}
		
		return null;
	}

	@Override
	public Activity replace(int id, Activity act) {
		Optional<Activity> catOpt = repo.findById(id);
		
		if(catOpt.isPresent()) {
			Activity managed = catOpt.get();
			managed.setName(act.getName());
			managed.setStartDate(act.getStartDate());
			managed.setEndDate(act.getEndDate());
			managed.setDescription(act.getDescription());
			//TODO: hard coding in an owner untill functionality is done
			User owner = uRepo.findById(2).get();
			if( owner != null) {
				
				managed.setOwner(owner);
			}
			//TODO: hard coding in an trip untill functionality is done
			Trip trip = tRepo.findById(1).get();
			if(trip != null) {
				
				managed.setTrip(trip);
			}
			//TODO: hard coding in an trip untill functionality is done
			Address add = aRepo.findById(1).get();
			if (add != null) {
				
				managed.setAddress(add);
			}
			return managed;
			
		}
		
		return null;
	}

	@Override
	public Activity create(Activity act) {
		try {
			//hard coding in an owner until full functionality is implimented.
			Optional<User> owner = uRepo.findById(2);
			if( owner.isPresent()) {
				
				act.setOwner(owner.get());
			}
			else {
				throw new Exception("Not a valid owner");
			}
			//hard coding in trip id
			Optional<Trip> trip = tRepo.findById(1);
			if(trip.isPresent()) {
				act.setTrip(trip.get());
			}
			else {
				throw new Exception("Not a valid Trip");
			}
			System.out.println(act.getStartDate());
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date startDate = sdf.parse(sDate);
//			Date endDate = sdf.parse(eDate);
//			act.setStartDate(startDate);
//			act.setEndDate(endDate);
			//TODO: activate this when address gets implimented
//			aRepo.saveAndFlush(act.getAddress());
			act = repo.saveAndFlush(act);
			
			owner.get().addOwnedActivity(act);
			uRepo.saveAndFlush(owner.get());
			trip.get().addActivity(act);
			tRepo.saveAndFlush(trip.get());
			return act;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(Activity act) {

		Optional<Activity> actOpt = repo.findById(act.getId());
		if(actOpt.isPresent()) {
			
				act = actOpt.get();
				act.setCategories(null);
				act.setUsers(null);
				act.setAddress(null);
				act.setOwner(null);
				act.setTrip(null);
				repo.delete(act);
			
			
		}
	}

//	@Override
//	public List<Activity> getAllByStartDate() {
//		return repo.findAll;
//	}

	@Override
	public List<Activity> getByNameOrDescription(String keyword) {
		String[] kwordArr = keyword.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		
		
		return repo.findByNameLikeOrDescriptionLike(kwordBuild.toString(), kwordBuild.toString());
	}

	@Override
	public List<Activity> getByOwnerName(String keyword) {
		String[] kwordArr = keyword.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		
		return repo.findByOwner_FnameLikeOrOwner_LnameLike(kwordBuild.toString(), kwordBuild.toString());
	}

	@Override
	public List<Activity> getByTripName(String keyword) {
		
		String[] kwordArr = keyword.split(" ");
		StringBuilder kwordBuild = new StringBuilder();
		for (String string : kwordArr) {
			string = "%"+string+"%";
			kwordBuild.append(string);
		}
		return repo.findByTrip_NameLike(kwordBuild.toString());
	}

	@Override
	public List<Activity> getByCategoryId(int catId) {
		return repo.findByCategories_Id(catId);
	}

}
