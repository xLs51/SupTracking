package com.supinfo.suptracking.dao;

import java.util.List;

import com.supinfo.suptracking.entities.GPSUser;

public interface GPSUserDao extends Dao<GPSUser>
{
	List<GPSUser> getTenLastEntriesOfAUser(long id);
}