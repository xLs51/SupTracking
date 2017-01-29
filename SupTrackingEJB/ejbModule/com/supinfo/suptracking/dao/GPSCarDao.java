package com.supinfo.suptracking.dao;

import java.util.List;

import com.supinfo.suptracking.entities.GPSCar;

public interface GPSCarDao extends Dao<GPSCar>
{
	List<GPSCar> getTenLastEntriesOfACar(long id);
}