package com.skilldistillery.travel.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Activity {

	//FIELDS
	//**********
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name="start_date")
	
	private String startDate;
	@Column(name="end_date")
	private String endDate;
	private String description;
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User owner;
	@ManyToMany(mappedBy="activities")
	@JsonIgnore
	private List<User> users;
	@ManyToOne
	@JoinColumn(name="trip_id")
	private Trip trip;
	@ManyToMany
	@JoinTable(name="activity_category",
			joinColumns=@JoinColumn(name="activity_id"),
			inverseJoinColumns=@JoinColumn(name="category_id"))
	private List<Category> categories;
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	
	//TODO comments
	//TODO expenses
	//TODO pictures
	
	
	//METHODS
	//******************
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
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
		Activity other = (Activity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", description=" + description + ", owner=" + owner+", categories=" + categories+ ", trip=" + trip + "]";
	}
	public Activity(int id, String name, String startDate, String endDate, String description, User owner, Trip trip,
			Address address) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.owner = owner;
		this.trip = trip;
		this.address = address;
	}
	public Activity() {
		super();
	}
	
	public List<Category> addCategory(Category cat){
		if(categories == null) {
			categories = new ArrayList<>();
		}
		if(!categories.contains(cat)) {
			categories.add(cat);
			cat.addActivity(this);
		}
		return categories;
	}
	public boolean removeCategory(Category cat){
		
		if(categories != null && categories.contains(cat)) {
			cat.removeActivity(this);
			return categories.remove(cat);
			
		}
		return false;
	}
	
	public List<User> addUser(User user){
		if(users == null) {
			users = new ArrayList<>();
		}
		if(!users.contains(user)) {
			users.add(user);
			user.addActivity(this);
		}
		return users;
	}
	public boolean removeUser(User user){
		
		if(users != null && users.contains(user)) {
			user.removeActivity(this);
			return users.remove(user);
			
		}
		return false;
	}
	
	
	
	
}
