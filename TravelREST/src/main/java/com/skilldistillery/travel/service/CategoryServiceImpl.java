package com.skilldistillery.travel.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.travel.entities.Category;
import com.skilldistillery.travel.repositories.CategoryRepo;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;
	
	@Override
	public List<Category> getAll() {
		return repo.findAll();
	}

	@Override
	public Category getById(int id) {
		Optional<Category> catOpt = repo.findById(id);
		if(catOpt.isPresent()) {
			return catOpt.get();
			
		}
		return null;
	}

	@Override
	public Category replace(int id, Category cat) {
		Optional<Category> catOpt = repo.findById(id);
		
		if(catOpt.isPresent()) {
			Category managed = catOpt.get();
			managed.setName(cat.getName());
			
			return managed;
			
		}
		
		return null;
	}

	@Override
	public Category create(Category cat) {
		try {
			repo.saveAndFlush(cat);
			return cat;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void delete(Category cat) {

		Optional<Category> catOpt = repo.findById(cat.getId());
		if(catOpt.isPresent() && catOpt.get().getActivities().isEmpty()) {
			
			try {
				repo.delete(cat);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
