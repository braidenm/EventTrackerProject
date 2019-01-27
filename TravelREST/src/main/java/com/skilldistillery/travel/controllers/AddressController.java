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

import com.skilldistillery.travel.entities.Address;
import com.skilldistillery.travel.service.AddressService;

@RestController
@RequestMapping("api")
public class AddressController {
	
	@Autowired
	private AddressService addService;
	
	@GetMapping("addresses")
	public List<Address> getAlladdresses(){
		return addService.getAll();
	}
	
	@GetMapping("addresses/{id}")
	public Address getactivitiesById(@PathVariable Integer id) {
		return addService.getById(id);
	}
	
	@PostMapping("addresses")
	public Address createNewactivities(@RequestBody Address add, HttpServletResponse response, HttpServletRequest request) {
		try {
			add = addService.create(add);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/");
			url.append(add.getId());
			response.addHeader("Location", url.toString());
		} catch (Exception e) {
			response.setStatus(400);
			add=null;
		}
		
		
		return add;
	}
	
	@PutMapping("addresses/{id}")
	public Address replaceactivities(@PathVariable Integer id, @RequestBody Address add) {
		return addService.replace(id, add);
	}
	
	
	@DeleteMapping("addresses/{id}")
	public boolean deleteactivities(@PathVariable Integer id, HttpServletResponse res) {
	
		try {
			addService.delete(addService.getById(id));;
			res.setStatus(204);
		} catch (Exception e) {
			res.setStatus(404);
			return false;
		}
			
		
		return true;
	}
	

}
