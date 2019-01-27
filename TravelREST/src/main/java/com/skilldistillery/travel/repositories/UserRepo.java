package com.skilldistillery.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.travel.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
