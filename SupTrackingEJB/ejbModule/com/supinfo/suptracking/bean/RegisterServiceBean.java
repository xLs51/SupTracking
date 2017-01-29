package com.supinfo.suptracking.bean;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.service.RegisterService;

@Stateless
public class RegisterServiceBean implements RegisterService
{
	@Override
	public User registerUser(User user) 
	{
		return DaoFactory.getUserDao().add(user);
	}

	@Override
	public boolean isUserExisting(String username)
	{
		return DaoFactory.getUserDao().getUserByUsername(username) != null;
	}

	@Override
	public boolean isPhoneNumberExisting(String phone)
	{
		return DaoFactory.getUserDao().getUserByPhoneNumber(phone) != null;
	}

	@Override
	public boolean isMailExisting(String mail)
	{
		return DaoFactory.getUserDao().getUserByMail(mail) != null;
	}
}