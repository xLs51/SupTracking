package com.supinfo.suptracking.dao;

import com.supinfo.suptracking.entities.User;

public interface UserDao extends Dao<User>
{
	User getUserByUsernameAndPassword(String email, String password);
	User getUserByUsername(String username);
	User getUserByMail(String mail);
	User getUserByPhoneNumber(String phone);
}
