package com.supinfo.suptracking.service;

import java.util.List;

import javax.ejb.Local;

import com.supinfo.suptracking.entities.GPSCar;
import com.supinfo.suptracking.entities.GPSUser;

@Local
public interface GPSService
{
	public void addGPSCarEntry(long id, double longitude, double latitude);
	public List<GPSCar> getTenLastEntriesOfACar(long id);
	void addGPSUserEntry(long id, double longitude, double latitude);
	List<GPSUser> getTenLastEntriesOfAUser(long id);
}
