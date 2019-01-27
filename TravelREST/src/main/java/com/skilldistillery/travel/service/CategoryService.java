package com.skilldistillery.travel.service;

import java.util.List;

import com.skilldistillery.travel.entities.Category;

public interface CategoryService {
	
	List<Category> getAll();
	Category getById(int id);
	Category replace(int id, Category cat);
	Category create(Category cat);
	void delete(Category cat);

}
