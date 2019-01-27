package com.skilldistillery.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.travel.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
