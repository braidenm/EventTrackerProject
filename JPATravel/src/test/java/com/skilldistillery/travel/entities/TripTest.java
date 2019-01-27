package com.skilldistillery.travel.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Trip trip;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("traveldb");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_user_mappings() {
		
		trip = em.find(Trip.class, 1);
		
		assertEquals("Test", trip.getName());
		assertEquals("Testing and stuff", trip.getDescription());
		assertNotNull(trip.getActivities());
		assertNotNull(trip.getUsers());
		
	}

}
