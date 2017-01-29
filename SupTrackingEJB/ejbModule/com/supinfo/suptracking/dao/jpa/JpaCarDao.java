package com.supinfo.suptracking.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;

import com.supinfo.suptracking.dao.CarDao;
import com.supinfo.suptracking.entities.Car;
import com.supinfo.suptracking.utils.PersistenceManager;

public class JpaCarDao extends AbstractJpaDao<Car> implements CarDao 
{
	public JpaCarDao() 
	{
		super(Car.class);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getAllByUser(long userId) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (List<Car>) em.createQuery("SELECT c FROM Car c WHERE c.user.id = " + userId).getResultList();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}
