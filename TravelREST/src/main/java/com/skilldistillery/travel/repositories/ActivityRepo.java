package com.skilldistillery.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.travel.entities.Activity;

public interface ActivityRepo extends JpaRepository<Activity, Integer> {

}
