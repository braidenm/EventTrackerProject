package com.skilldistillery.travel.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User {
	
	//Fields
	//*************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="last_name")
	private String lname;
	@Column(name="first_name")
	private String fname;
	private String username;
	private String password;
	private boolean active;
	private boolean banned;
	private String role;
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	@ManyToMany
	@JoinTable(name="user_trip",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="trip_id"))
	@JsonIgnore
	private List<Trip> trips;
	@OneToMany(mappedBy="owner")
	@JsonIgnore
	private List<Trip> ownedTrips;
	@ManyToMany
	@JoinTable(name="activity_user",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="activity_id"))
	@JsonIgnore
	private List<Activity> activities;
	@OneToMany(mappedBy="owner")
	@JsonIgnore
	private List<Activity> ownedActivities;
	
	//TODO set up pictures
	//TODO set up comments
	
	//METHODS
	//***************
	
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	public List<Trip> getOwnedTrips() {
		return ownedTrips;
	}
	public void setOwnedTrips(List<Trip> ownedTrips) {
		this.ownedTrips = ownedTrips;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public List<Activity> getOwnedActivities() {
		return ownedActivities;
	}
	public void setOwnedActivities(List<Activity> ownedActivities) {
		this.ownedActivities = ownedActivities;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isBanned() {
		return banned;
	}
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", lname=" + lname + ", fname=" + fname + ", username=" + username + ", password="
				+ password + ", active=" + active + ", banned=" + banned + ", role=" + role + ", address=" + address
				+ "]";
	}
	public User(int id, String lname, String fname, String username, String password, boolean active, boolean banned,
			String role, Address address) {
		super();
		this.id = id;
		this.lname = lname;
		this.fname = fname;
		this.username = username;
		this.password = password;
		this.active = active;
		this.banned = banned;
		this.role = role;
		this.address = address;
	}
	public User() {
		super();
	}
	public List<Activity> addActivity(Activity act){
		if(activities == null) {
			activities = new ArrayList<>();
		}
		if(!activities.contains(act)) {
			activities.add(act);
			act.addUser(this);
		}
		return activities;
	}
	public boolean removeActivity(Activity act){
		
		if(activities != null && activities.contains(act)) {
			act.removeUser(this);
			return activities.remove(act);
			
		}
		return false;
	}
	public List<Activity> addOwnedActivity(Activity act){
		if(ownedActivities == null) {
			ownedActivities = new ArrayList<>();
		}
		if(!ownedActivities.contains(act)) {
			ownedActivities.add(act);
		}
		return ownedActivities;
	}
	public boolean removeOwnedActivity(Activity act){
		
		if(ownedActivities != null && ownedActivities.contains(act)) {
			return ownedActivities.remove(act);
			
		}
		return false;
	}
	public List<Trip> addOwnedTrip(Trip trip){
		if(ownedTrips == null) {
			ownedTrips = new ArrayList<>();
		}
		if(!ownedTrips.contains(trip)) {
			ownedTrips.add(trip);
		}
		return ownedTrips;
	}
	public boolean removeOwnedTrip(Trip trip){
		
		if(ownedTrips != null && ownedTrips.contains(trip)) {
			return ownedTrips.remove(trip);
			
		}
		return false;
	}
	public List<Trip> addTrip(Trip trip){
		if(trips == null) {
			trips = new ArrayList<>();
		}
		if(!trips.contains(trip)) {
			trips.add(trip);
			trip.addUser(this);
		}
		return trips;
	}
	public boolean removeTrip(Trip trip){
		
		if(trips != null && trips.contains(trip)) {
			trip.removeUser(this);
			return trips.remove(trip);
			
		}
		return false;
	}
	
	
}
