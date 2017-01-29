package com.supinfo.suptracking.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.supinfo.suptracking.entities.GPSCar;
import com.supinfo.suptracking.entities.GPSUser;
import com.supinfo.suptracking.service.GPSService;

@WebService(name="GPSCarLocationService", serviceName="GPSCarLocation")
public class GPSWebService
{	
	@EJB
	GPSService gpsService;
	
	///////////////////////////////////////////////////////
	/////	CAR
	///////////////////////////////////////////////////////
	@WebMethod(operationName="addGPSCarEntry")
	public void addGPSCarEntry(@WebParam(name="id") Long id, @WebParam(name="lat") Double latitude, @WebParam(name="long") Double longitude)
	{
		gpsService.addGPSCarEntry(id, longitude, latitude);
	}
	
	@WebMethod(operationName="getGPSCarEntry")
	public List<GPSCar> getGPSCarEntry(@WebParam(name="id") Long id)
	{
		return gpsService.getTenLastEntriesOfACar(id);
	}
	
	///////////////////////////////////////////////////////
	/////	USER
	///////////////////////////////////////////////////////
	@WebMethod(operationName="addGPSUSerEntry")
	public void addGPSUserEntry(@WebParam(name="id") Long id, @WebParam(name="lat") Double latitude, @WebParam(name="long") Double longitude)
	{
		gpsService.addGPSUserEntry(id, longitude, latitude);
	}
	
	@WebMethod(operationName="getGPSUserEntry")
	public List<GPSUser> getGPSUserEntry(@WebParam(name="id") Long id)
	{
		return gpsService.getTenLastEntriesOfAUser(id);
	}
}
