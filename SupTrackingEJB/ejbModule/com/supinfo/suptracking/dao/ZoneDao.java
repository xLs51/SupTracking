package com.supinfo.suptracking.dao;

import java.util.List;

import com.supinfo.suptracking.entities.Zone;

public interface ZoneDao extends Dao<Zone>
{
	List<Zone> getAllByUser(long userId);
}
