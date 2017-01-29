package com.supinfo.suptracking.service;

import java.util.List;

import javax.ejb.Local;

import com.supinfo.suptracking.entities.Zone;

@Local
public interface ZoneService
{
	public List<Zone> getAll();
	public Zone getById(Long id);
	public void remove(Long id);
	public void update(Zone zone);
	public Zone add(Zone zone);
	List<Zone> getAllByUser(long userId);
}
