package com.skilldistillery.travel.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address {
		//Fields
		//*************
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String street;
		@Column(name="street_2")
		private String street2;
		private String city;
		private String state;
		private String zip;
		private String country;
		private String description;
		@OneToMany(mappedBy="address")
		@JsonIgnore
		private List<Activity> activities;
		
		//TODO set up list of activities 
		
		//METHODS
		//*******************
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getStreet2() {
			return street2;
		}
		public void setStreet2(String street2) {
			this.street2 = street2;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
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
			Address other = (Address) obj;
			if (id != other.id)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Address [id=" + id + ", street=" + street + ", street2=" + street2 + ", city=" + city + ", state="
					+ state + ", zip=" + zip + ", country=" + country + ", description=" + description + "]";
		}
		public Address(int id, String street, String street2, String city, String state, String zip, String country,
				String description) {
			super();
			this.id = id;
			this.street = street;
			this.street2 = street2;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.country = country;
			this.description = description;
		}
		public Address() {
			super();
		}
		public List<Activity> getActivities() {
			return activities;
		}
		public void setActivities(List<Activity> activities) {
			this.activities = activities;
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
