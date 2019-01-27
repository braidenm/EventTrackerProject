package com.skilldistillery.travel.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.travel.entities.Address;
import com.skilldistillery.travel.repositories.AddressRepo;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo repo;
	
	@Override
	public List<Address> getAll() {
		return repo.findAll();
	}

	@Override
	public Address getById(int id) {
		Optional<Address> addOpt = repo.findById(id);
		if(addOpt.isPresent()) {
			return addOpt.get();
			
		}
		
		return null;
	}

	@Override
	public Address replace(int id, Address add) {
		Optional<Address> addOpt = repo.findById(id);
		
		if(addOpt.isPresent()) {
			Address managed = addOpt.get();
			managed.setStreet(add.getStreet());
			managed.setStreet2(add.getStreet2());
			managed.setCity(add.getCity());
			managed.setState(add.getState());
			managed.setZip(add.getZip());
			managed.setCountry(add.getCountry());
			managed.setDescription(add.getDescription());
			return managed;
			
		}
		
		
		return null;
	}

	@Override
	public Address create(Address address) {
		try {
			repo.saveAndFlush(address);
			return address;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}

	@Override
	public void delete(Address address) {
		Optional<Address> addOpt = repo.findById(address.getId());
		if(addOpt.isPresent() && addOpt.get().getActivities().isEmpty()) {
			
			try {
				repo.delete(address);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
