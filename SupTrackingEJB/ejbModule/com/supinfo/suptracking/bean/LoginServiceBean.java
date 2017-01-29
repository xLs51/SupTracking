package com.supinfo.suptracking.bean;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.service.LoginService;

@Stateless
public class LoginServiceBean implements LoginService
{
	@Override
	public User connect(String username, String password) 
	{
		return DaoFactory.getUserDao().getUserByUsernameAndPassword(username, password);
	}
}
