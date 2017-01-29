package com.supinfo.suptracking.service;

import java.util.List;

import javax.ejb.Local;

import com.supinfo.suptracking.entities.Car;

@Local
public interface CarService
{
	public List<Car> getAll();
	public Car getById(Long id);
	public void remove(Long id);
	public void update(Car car);
	public Car add(Car car);
	List<Car> getAllByUser(long userId);
}
