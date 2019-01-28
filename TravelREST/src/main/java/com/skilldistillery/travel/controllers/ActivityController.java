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

import com.skilldistillery.travel.entities.Activity;
import com.skilldistillery.travel.service.ActivityService;

@RestController
@RequestMapping("api")
public class ActivityController {
	
	@Autowired
	private ActivityService actService;
	
	@GetMapping("activities")
	public List<Activity> getAllactivities(){
		return actService.getAll();
	}
	
	@GetMapping("activities/{id}")
	public Activity getactivitiesById(@PathVariable Integer id, HttpServletResponse res) {
		
		Activity act = actService.getById(id);
		if (act != null) {
			res.setStatus(201);
			return act;
		}
		res.setStatus(404);
		return null;
	}
	
	@PostMapping("activities")
	public Activity createNewactivities(@RequestBody Activity act, HttpServletResponse response, HttpServletRequest request) {
		try {
			System.out.println(act);
			act = actService.create(act);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/");
			url.append(act.getId());
			response.addHeader("Location", url.toString());
		} catch (Exception e) {
			response.setStatus(400);
			act=null;
		}
		
		
		return act;
	}
	
	@PutMapping("activities/{id}")
	public Activity replaceactivities(@PathVariable Integer id, @RequestBody Activity act, HttpServletResponse res) {
		 act = actService.replace(id, act);
		if(act != null) {
			res.setStatus(201);
			return act;
		}
		res.setStatus(404);
		return null;
	}
	
	
	@DeleteMapping("activities/{id}")
	public boolean deleteactivities(@PathVariable Integer id, HttpServletResponse res) {
	
		try {
			actService.delete(actService.getById(id));;
			res.setStatus(204);
		} catch (Exception e) {
			res.setStatus(404);
			return false;
		}
			
		
		return true;
	}
	
//	@GetMapping("activities/date")
//	public List<Activity> getAllActivityByDate(){
//		return actService.getAllByStartDate();
//	}
	
	@GetMapping("activities/search/{kword}")
	public List<Activity> searchByName(@PathVariable String kword, HttpServletResponse res){
		
		
		List<Activity> actList = actService.getByNameOrDescription(kword);
		if(!actList.isEmpty()) {
			res.setStatus(201);
			return actList;
		}
		res.setStatus(404);
		return null;
	}
	@GetMapping("activities/search/owner/{kword}")
	public List<Activity> searchByOwner(@PathVariable String kword, HttpServletResponse res){
		
		
		List<Activity> actList = actService.getByOwnerName(kword);
		if(!actList.isEmpty()) {
			res.setStatus(201);
			return actList;
		}
		res.setStatus(404);
		return null;
	}
	
	@GetMapping("activities/search/trip/{kword}")
	public List<Activity> searchByTrip(@PathVariable String kword, HttpServletResponse res){
		
		
		List<Activity> actList = actService.getByTripName(kword);
		if(!actList.isEmpty()) {
			res.setStatus(201);
			return actList;
		}
		res.setStatus(404);
		return null;
	}
	@GetMapping("activities/search/category/{catId}")
	public List<Activity> searchByCategory(@PathVariable Integer catId, HttpServletResponse res){
		
		
		List<Activity> actList = actService.getByCategoryId(catId);
		if(!actList.isEmpty()) {
			res.setStatus(201);
			return actList;
		}
		res.setStatus(404);
		return null;
	}
	

	
	
	
	
}
