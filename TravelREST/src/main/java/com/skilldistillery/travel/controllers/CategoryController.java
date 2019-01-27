package com.skilldistillery.travel.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.travel.entities.Category;
import com.skilldistillery.travel.service.CategoryService;

@RestController
@RequestMapping("api")
public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	@GetMapping("categories")
	public List<Category> getAllCategories(){
		return catService.getAll();
	}
	
	@GetMapping("categories/{id}")
	public Category getCategoriesById(@PathVariable Integer id, HttpServletResponse res) {
		
		Category cat = catService.getById(id);
		if (cat != null) {
			res.setStatus(201);
			return cat;
		}
		res.setStatus(404);
		return null;
	}
	
	@PostMapping("categories")
	public Category createNewCategories(@RequestBody Category cat, HttpServletResponse response, HttpServletRequest request) {
		try {
			cat = catService.create(cat);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/");
			url.append(cat.getId());
			response.addHeader("Location", url.toString());
		} catch (Exception e) {
			response.setStatus(400);
			cat=null;
		}
		
		
		return cat;
	}
	
	@PutMapping("categories/{id}")
	public Category replaceCategories(@PathVariable Integer id, @RequestBody Category cat, HttpServletResponse res) {
		 cat = catService.replace(id, cat);
		 if(cat != null) {
			res.setStatus(201);
			return cat;
		 }
		
		res.setStatus(404);
		return null;
	}
	
	
	@DeleteMapping("categories/{id}")
	public boolean deleteCategories(@PathVariable Integer id, HttpServletResponse res) {
	
		try {
			catService.delete(catService.getById(id));;
			res.setStatus(201);
		} catch (Exception e) {
			res.setStatus(404);
			return false;
		}
			
		
		return true;
	}
	


}
