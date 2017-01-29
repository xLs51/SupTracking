package com.supinfo.suptracking.service;

import java.util.List;

import javax.ejb.Local;

import com.supinfo.suptracking.entities.GPSZone;

@Local
public interface GPSZoneService
{
	public GPSZone add(GPSZone gps);
	public void remove(Long id);
	public GPSZone getByZone(Long zoneId);
	public List<GPSZone> getTenLastEntriesOfAZone(long id);
}
