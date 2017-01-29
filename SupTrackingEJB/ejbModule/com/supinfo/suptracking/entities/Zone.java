package com.supinfo.suptracking.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Entity
public class Zone implements Serializable
{
	private static final long serialVersionUID = 268341765511485726L;
	
	public static final String JSON_ID = "id";
	public static final String JSON_GPS = "gps";
	public static final String JSON_RADIUS = "radius";
	public static final String JSON_FIRSTTIME = "firstTime";
	public static final String JSON_SECONDTIME = "secondTime";

	/////////////////////////////////////
	////	ATTRIBUTES
	/////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false)
	private double radius;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstTime;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date secondTime;
	
	@ManyToOne
	@JoinColumn(name="user_fk")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="car_fk")
	private Car car;
	
	@OneToMany(mappedBy="zone_fk", orphanRemoval = true)
	private Collection<GPSZone> gps;
	
	/////////////////////////////////////
	////	GETTERS AND SETTERS
	/////////////////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public Date getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}
	public Date getSecondTime() {
		return secondTime;
	}
	public void setSecondTime(Date secondTime) {
		this.secondTime = secondTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Collection<GPSZone> getGps() {
		return gps;
	}
	public void setGps(Collection<GPSZone> gps) {
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
			json.put(JSON_RADIUS, this.getRadius());
			json.put(JSON_FIRSTTIME, this.getFirstTime());
			json.put(JSON_SECONDTIME, this.getSecondTime());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		} 
		
		return json;
	}
	
	public static Zone toObject(JSONObject object)
	{
		Zone zone = new Zone();
		
		try 
		{
			zone.setId(object.getInt(JSON_ID));
			zone.setRadius(object.getDouble(JSON_RADIUS));
			zone.setFirstTime((Date)object.get(JSON_FIRSTTIME));
			zone.setSecondTime((Date)object.get(JSON_SECONDTIME));
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return zone;
	}
}