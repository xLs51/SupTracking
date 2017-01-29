package com.supinfo.suptracking.bean;

import java.util.List;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.Car;
import com.supinfo.suptracking.entities.GPSCar;
import com.supinfo.suptracking.entities.GPSUser;
import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.service.GPSService;

@Stateless
public class GPSServiceBean implements GPSService
{
	@Override
	public void addGPSCarEntry(long id, double longitude, double latitude)
	{
		GPSCar gpsCar = new GPSCar();
		gpsCar.setLatitude(latitude);
		gpsCar.setLongitude(longitude);
		
		Car car = DaoFactory.getCarDao().getById(id);		
		gpsCar.setCar_fk(car);
		
		DaoFactory.getGPSCarDao().add(gpsCar);
	}
	
	@Override
	public List<GPSCar> getTenLastEntriesOfACar(long id)
	{
		return DaoFactory.getGPSCarDao().getTenLastEntriesOfACar(id);
	}
	
	@Override
	public void addGPSUserEntry(long id, double longitude, double latitude)
	{
		GPSUser gpsUser = new GPSUser();
		gpsUser.setLatitude(latitude);
		gpsUser.setLongitude(longitude);
		
		User user = DaoFactory.getUserDao().getById(id);		
		gpsUser.setUser_fk(user);
		
		DaoFactory.getGPSUserDao().add(gpsUser);
	}
	
	@Override
	public List<GPSUser> getTenLastEntriesOfAUser(long id)
	{
		return DaoFactory.getGPSUserDao().getTenLastEntriesOfAUser(id);
	}
}