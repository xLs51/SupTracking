package com.supinfo.suptracking.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;

import com.supinfo.suptracking.dao.GPSZoneDao;
import com.supinfo.suptracking.entities.GPSZone;
import com.supinfo.suptracking.utils.PersistenceManager;

public class JpaGPSZoneDao  extends AbstractJpaDao<GPSZone> implements GPSZoneDao 
{
	public JpaGPSZoneDao() 
	{
		super(GPSZone.class);
	}

	@Override
	public GPSZone getByZone(Long zoneId)
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (GPSZone) em.createQuery("SELECT g FROM GPSZone g WHERE g.zone_fk.id = " + zoneId).getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GPSZone> getTenLastEntriesOfAZone(long id) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (List<GPSZone>) em.createQuery("SELECT g FROM GPSZone g WHERE g.zone_fk.id = " + id + " ORDER BY g.timestamp DESC").setMaxResults(10).getResultList();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}