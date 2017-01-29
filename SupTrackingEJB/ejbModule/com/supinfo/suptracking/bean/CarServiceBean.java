package com.supinfo.suptracking.bean;

import java.util.List;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.Car;
import com.supinfo.suptracking.service.CarService;

@Stateless
public class CarServiceBean implements CarService
{
	@Override
	public List<Car> getAll()
	{
		return DaoFactory.getCarDao().getAll();
	}
	
	@Override
	public List<Car> getAllByUser(long userId)
	{
		return DaoFactory.getCarDao().getAllByUser(userId);
	}

	@Override
	public Car getById(Long id)
	{
		return DaoFactory.getCarDao().getById(id);
	}

	@Override
	public void remove(Long id)
	{
		DaoFactory.getCarDao().remove(id);
	}
	
	@Override
	public void update(Car car) 
	{
		DaoFactory.getCarDao().update(car);
	}
	
	@Override
	public Car add(Car car) 
	{
		return DaoFactory.getCarDao().add(car);
	}
}
