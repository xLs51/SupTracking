package com.supinfo.suptracking.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;

import com.supinfo.suptracking.dao.GPSCarDao;
import com.supinfo.suptracking.entities.GPSCar;
import com.supinfo.suptracking.utils.PersistenceManager;

public class JpaGPSCarDao  extends AbstractJpaDao<GPSCar> implements GPSCarDao 
{
	public JpaGPSCarDao() 
	{
		super(GPSCar.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GPSCar> getTenLastEntriesOfACar(long id) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (List<GPSCar>) em.createQuery("SELECT c FROM GPSCar c WHERE c.car_fk.id = " + id + " ORDER BY c.timestamp DESC").setMaxResults(10).getResultList();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}