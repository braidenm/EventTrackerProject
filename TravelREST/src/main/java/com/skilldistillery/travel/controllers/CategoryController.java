package com.skilldistillery.travel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.travel.service.CategoryService;

@RestController
@RequestMapping("api")
public class CategoryController {
	
	@Autowired
	private CategoryService catService;

}
