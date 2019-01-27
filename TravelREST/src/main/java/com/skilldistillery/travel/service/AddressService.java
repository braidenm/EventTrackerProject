package com.skilldistillery.travel.service;

import java.util.List;

import com.skilldistillery.travel.entities.Address;

public interface AddressService {
	
	List<Address> getAll();
	Address getById(int id);
	Address replace(int id, Address address);
	Address create(Address address);
	void delete(Address address);

}
