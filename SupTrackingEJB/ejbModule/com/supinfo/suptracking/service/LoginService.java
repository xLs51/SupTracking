package com.supinfo.suptracking.service;

import javax.ejb.Local;

import com.supinfo.suptracking.entities.User;

@Local
public interface LoginService
{
	public User connect(String email, String password);
}
