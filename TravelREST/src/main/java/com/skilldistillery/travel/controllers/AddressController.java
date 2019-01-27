package com.skilldistillery.travel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.travel.service.AddressService;

@RestController
@RequestMapping("api")
public class AddressController {
	
	@Autowired
	private AddressService addService;

}
