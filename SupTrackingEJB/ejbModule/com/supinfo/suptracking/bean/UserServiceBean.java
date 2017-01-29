package com.supinfo.suptracking.bean;

import java.util.List;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.service.UserService;

@Stateless
public class UserServiceBean implements UserService
{
	@Override
	public List<User> getAll()
	{
		return DaoFactory.getUserDao().getAll();
	}

	@Override
	public User getById(Long id)
	{
		return DaoFactory.getUserDao().getById(id);
	}

	@Override
	public void remove(Long id)
	{
		DaoFactory.getUserDao().remove(id);
	}
	
	@Override
	public void update(User user) 
	{
		DaoFactory.getUserDao().update(user);
	}
}
