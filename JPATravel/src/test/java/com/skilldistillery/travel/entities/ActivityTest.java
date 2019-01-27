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

class ActivityTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Activity activity;
	
	
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
		
		activity = em.find(Activity.class, 1);
		
		assertEquals("Test Activity", activity.getName());
		assertEquals("testing this activity", activity.getDescription());
		assertEquals(1, activity.getTrip().getId());
		assertNotNull(activity.getCategories());
		assertNotNull(activity.getUsers());
		
	}

}
