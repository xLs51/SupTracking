package com.supinfo.suptracking.bean;

import java.util.List;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.Zone;
import com.supinfo.suptracking.service.ZoneService;

@Stateless
public class ZoneServiceBean implements ZoneService
{
	@Override
	public List<Zone> getAll()
	{
		return DaoFactory.getZoneDao().getAll();
	}
	
	@Override
	public List<Zone> getAllByUser(long userId)
	{
		return DaoFactory.getZoneDao().getAllByUser(userId);
	}

	@Override
	public Zone getById(Long id)
	{
		return DaoFactory.getZoneDao().getById(id);
	}
	
	@Override
	public void remove(Long id)
	{
		DaoFactory.getZoneDao().remove(id);
	}
	
	@Override
	public void update(Zone zone) 
	{
		DaoFactory.getZoneDao().update(zone);
	}
	
	@Override
	public Zone add(Zone zone) 
	{
		return DaoFactory.getZoneDao().add(zone);
	}
}
