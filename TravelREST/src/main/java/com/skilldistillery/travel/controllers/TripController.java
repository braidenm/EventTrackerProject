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

import com.skilldistillery.travel.entities.Trip;
import com.skilldistillery.travel.service.TripService;

@RestController
@RequestMapping("api")
public class TripController {
	
	@Autowired
	private TripService tripService;
	
	@GetMapping("trips")
	public List<Trip> getAllTrips(){
		return tripService.getAll();
	}
	
	@GetMapping("trips/{id}")
	public Trip getTripsById(@PathVariable Integer id, HttpServletResponse response) {
		if(tripService.getById(id) != null) {
			response.setStatus(201);
			return tripService.getById(id);
		}
		response.setStatus(404);
		return null;
	}
	
	@PostMapping("trips")
	public Trip createNewTrips(@RequestBody Trip trip, HttpServletResponse response, HttpServletRequest request) {
		try {
			trip = tripService.create(trip);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/");
			url.append(trip.getId());
			response.addHeader("Location", url.toString());
		} catch (Exception e) {
			response.setStatus(400);
			trip=null;
		}
		
		
		return trip;
	}
	
	@PutMapping("trips/{id}")
	public Trip replaceTrips(@PathVariable Integer id, @RequestBody Trip trip, HttpServletResponse res) {
		trip = tripService.replace(id, trip);
		if(trip != null) {
			res.setStatus(201);
			return trip;
		}
		res.setStatus(404);
		return null;
	}
	
	
	@DeleteMapping("trips/{id}")
	public boolean deleteTrips(@PathVariable Integer id, HttpServletResponse res) {
	
		try {
			tripService.delete(tripService.getById(id));;
			res.setStatus(201);
		} catch (Exception e) {
			res.setStatus(404);
			return false;
		}
			
		
		return true;
	}
	

}
