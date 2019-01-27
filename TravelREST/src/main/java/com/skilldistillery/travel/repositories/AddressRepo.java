package com.skilldistillery.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.travel.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
