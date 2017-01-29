package com.supinfo.suptracking.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Car implements Serializable
{
	private static final long serialVersionUID = 3529806990445665233L;
	
	public static final String JSON_ID = "id";
	public static final String JSON_NAME = "name";
	public static final String JSON_BRAND = "brand";
	public static final String JSON_YEAR = "year";
	public static final String JSON_GPS = "gps";

	/////////////////////////////////////
	////	ATTRIBUTES
	/////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	@Column(nullable=false)
	private String name;
	
	@NotEmpty
	@Column(nullable=false)
	private String brand;
	
	@Column(nullable=false)
	private int year;
	
	@ManyToOne
	@JoinColumn(name="user_fk")
	private User user;
	
	@OneToMany(mappedBy="car_fk", orphanRemoval = true)
	private Collection<GPSCar> gps;
	
	
	/////////////////////////////////////
	////	GETTERS AND SETTERS
	/////////////////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Collection<GPSCar> getGps() {
		return gps;
	}
	public void setGps(Collection<GPSCar> gps) {
		this.gps = gps;
	}
	
	/////////////////////////////////////
	////	JSON HANDLER
	/////////////////////////////////////
	public JSONObject toJson()
	{
		JSONObject json = new JSONObject();
		
		try
		{
			json.put(JSON_ID, this.getId());
			json.put(JSON_NAME, this.getName());
			json.put(JSON_BRAND, this.getBrand());
			json.put(JSON_YEAR, this.getYear());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		} 
		
		return json;
	}
	
	public static Car toObject(JSONObject object)
	{
		Car car = new Car();
		
		try 
		{
			car.setId(object.getInt(JSON_ID));
			car.setName(object.getString(JSON_NAME));
			car.setBrand(object.getString(JSON_BRAND));
			car.setYear(object.getInt(JSON_YEAR));
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return car;
	}
}