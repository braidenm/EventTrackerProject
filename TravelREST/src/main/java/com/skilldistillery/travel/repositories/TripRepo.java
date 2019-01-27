package com.skilldistillery.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.travel.entities.Trip;

public interface TripRepo extends JpaRepository<Trip, Integer> {

}
