package com.skilldistillery.travel.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.travel.entities.Activity;
import com.skilldistillery.travel.repositories.ActivityRepo;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepo repo;

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
			managed.setOwner(act.getOwner());
			managed.setTrip(act.getTrip());
			managed.setAddress(act.getAddress());
			return managed;
			
		}
		
		return null;
	}

	@Override
	public Activity create(Activity act) {
		try {
			repo.saveAndFlush(act);
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
			
			try {
				repo.delete(act);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
