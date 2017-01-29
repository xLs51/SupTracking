package com.supinfo.suptracking.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;

import com.supinfo.suptracking.dao.GPSUserDao;
import com.supinfo.suptracking.entities.GPSUser;
import com.supinfo.suptracking.utils.PersistenceManager;

public class JpaGPSUserDao  extends AbstractJpaDao<GPSUser> implements GPSUserDao 
{
	public JpaGPSUserDao() 
	{
		super(GPSUser.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GPSUser> getTenLastEntriesOfAUser(long id) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (List<GPSUser>) em.createQuery("SELECT c FROM GPSUser c WHERE c.user_fk.id = " + id + " ORDER BY c.timestamp DESC").setMaxResults(10).getResultList();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}