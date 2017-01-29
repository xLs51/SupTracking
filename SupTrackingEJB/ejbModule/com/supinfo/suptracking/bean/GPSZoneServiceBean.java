package com.supinfo.suptracking.bean;

import java.util.List;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.GPSZone;
import com.supinfo.suptracking.service.GPSZoneService;

@Stateless
public class GPSZoneServiceBean implements GPSZoneService
{
	@Override
	public GPSZone add(GPSZone gps) 
	{
		return DaoFactory.getGPSZoneDao().add(gps);
	}
	
	@Override
	public void remove(Long id)
	{
		DaoFactory.getGPSZoneDao().remove(id);
	}
	
	@Override
	public GPSZone getByZone(Long zoneId)
	{
		return DaoFactory.getGPSZoneDao().getByZone(zoneId);
	}
	
	@Override
	public List<GPSZone> getTenLastEntriesOfAZone(long id)
	{
		return DaoFactory.getGPSZoneDao().getTenLastEntriesOfAZone(id);
	}
}