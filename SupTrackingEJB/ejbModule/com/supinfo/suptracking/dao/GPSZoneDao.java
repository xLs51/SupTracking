package com.supinfo.suptracking.dao;

import java.util.List;

import com.supinfo.suptracking.entities.GPSZone;

public interface GPSZoneDao extends Dao<GPSZone>
{
	public GPSZone getByZone(Long zoneId);
	List<GPSZone> getTenLastEntriesOfAZone(long id);
}