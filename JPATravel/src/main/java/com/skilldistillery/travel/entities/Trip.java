package com.skilldistillery.travel.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trip {
	
		//Fields
		//*************
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String name;
		private String description;
		@ManyToOne
		@JoinColumn(name="owner_id")
		private User owner;
		@ManyToMany(mappedBy="trips")
		@JsonIgnore
		private List<User> users;
		@OneToMany(mappedBy="trip")
		@JsonIgnore
		private List<Activity> activities;
		//TODO set up pictures
		//TODO set up comments
		//TODO set up expenses
		
		//METHODS
		//*****************
		
		@Override
		public String toString() {
			return "Trip [id=" + id + ", name=" + name + ", description=" + description + ", owner=" + owner;
		}

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

		public List<Activity> getActivities() {
			return activities;
		}

		public void setActivities(List<Activity> activities) {
			this.activities = activities;
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
			Trip other = (Trip) obj;
			if (id != other.id)
				return false;
			return true;
		}

		public Trip(int id, String name, String description, User owner) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.owner = owner;
		}

		public Trip() {
			super();
		}
		
		public List<User> addUser(User user){
			if(users == null) {
				users = new ArrayList<>();
			}
			if(!users.contains(user)) {
				users.add(user);
				user.addTrip(this);
			}
			return users;
		}
		public boolean removeUser(User user){
			
			if(users != null && users.contains(user)) {
				user.removeTrip(this);
				return users.remove(user);
				
			}
			return false;
		}
		
		public List<Activity> addActivity(Activity act){
			if(activities == null) {
				activities = new ArrayList<>();
			}
			if(!activities.contains(act)) {
				activities.add(act);
			}
			return activities;
		}
		public boolean removeActivity(Activity act){
			
			if(activities != null && activities.contains(act)) {
				return activities.remove(act);
				
			}
			return false;
		}
		

}
