package com.supinfo.suptracking.dao.jpa;

import javax.persistence.NoResultException;

import com.supinfo.suptracking.dao.UserDao;
import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.utils.PersistenceManager;

public class JpaUserDao  extends AbstractJpaDao<User> implements UserDao 
{
	public JpaUserDao() 
	{
		super(User.class);		
	}
	
	@Override
	public User getUserByUsernameAndPassword(String username, String password) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (User) em.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "' AND u.password = '"+ password +"'").getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	@Override
	public User getUserByUsername(String username) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (User) em.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "'").getSingleResult();
		}
		catch(NoResultException e) 
		{
			return null;
		}
	}
	
	@Override
	public User getUserByMail(String mail) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (User) em.createQuery("SELECT u FROM User u WHERE u.mail = '" + mail + "'").getSingleResult();
		} catch(NoResultException e) 
		{
			return null;
		}
	}
	
	@Override
	public User getUserByPhoneNumber(String phone)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try
		{
			return (User) em.createQuery("SELECT u FROM User u WHERE u.phone = '" + phone + "'").getSingleResult();
		} catch(NoResultException e) 
		{
			return null;
		}
	}
}
