package com.supinfo.suptracking.service;

import java.util.List;

import javax.ejb.Local;

import com.supinfo.suptracking.entities.User;

@Local
public interface UserService
{
	public List<User> getAll();
	public User getById(Long id);
	public void remove(Long id);
	public void update(User user);
}
