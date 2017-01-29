package com.supinfo.suptracking.dao;

import java.util.List;

import com.supinfo.suptracking.entities.Car;

public interface CarDao extends Dao<Car>
{
	List<Car> getAllByUser(long userId);
}