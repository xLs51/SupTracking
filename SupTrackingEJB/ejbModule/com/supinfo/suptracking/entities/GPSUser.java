package com.supinfo.suptracking.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Entity
public class GPSUser implements Serializable
{
	private static final long serialVersionUID = 1090068458040362218L;

	public static final String JSON_ID = "id";
	public static final String JSON_LATITUDE = "latitude";
	public static final String JSON_LONGITUDE = "longitude";
	public static final String JSON_TIMESTAMP = "timestamp";
	public static final String JSON_USER = "user";
	
	/////////////////////////////////////
	////	ATTRIBUTES
	/////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false)
	private double latitude;
	
	@Column(nullable=false)
	private double longitude;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@PrePersist
	protected void onCreate() {
		timestamp = new Date();
	}
	
	@ManyToOne
	@JoinColumn(name="user_fk")
	private User user_fk;
	
	/////////////////////////////////////
	////	GETTERS AND SETTERS
	/////////////////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public User getUser_fk() {
		return user_fk;
	}
	public void setUser_fk(User user_fk) {
		this.user_fk = user_fk;
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
			json.put(JSON_LATITUDE, this.getLatitude());
			json.put(JSON_LONGITUDE, this.getLongitude());
			json.put(JSON_TIMESTAMP, this.getTimestamp());
			json.put(JSON_USER, this.getUser_fk().toJson());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		} 
		
		return json;
	}
	
	public static GPSUser toObject(JSONObject object)
	{
		GPSUser gps = new GPSUser();
		
		try 
		{
			gps.setId(object.getInt(JSON_ID));
			gps.setLatitude(object.getDouble(JSON_LATITUDE));
			gps.setLongitude(object.getDouble(JSON_LATITUDE));
			gps.setTimestamp((Date)object.get(JSON_TIMESTAMP));
			gps.setUser_fk(User.toObject(object.getJSONObject(JSON_USER)));
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return gps;
	}
}
