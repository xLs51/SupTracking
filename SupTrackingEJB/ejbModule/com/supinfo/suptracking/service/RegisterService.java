package com.supinfo.suptracking.service;

import javax.ejb.Local;

import com.supinfo.suptracking.entities.User;

@Local
public interface RegisterService
{
	public boolean isUserExisting(String username);
	public User registerUser(User user);
	public boolean isPhoneNumberExisting(String mail);
	public boolean isMailExisting(String mail);
}
