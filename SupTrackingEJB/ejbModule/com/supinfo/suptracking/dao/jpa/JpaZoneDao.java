package com.supinfo.suptracking.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;

import com.supinfo.suptracking.dao.ZoneDao;
import com.supinfo.suptracking.entities.Zone;
import com.supinfo.suptracking.utils.PersistenceManager;

public class JpaZoneDao extends AbstractJpaDao<Zone> implements ZoneDao 
{
	public JpaZoneDao() 
	{
		super(Zone.class);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Zone> getAllByUser(long userId) 
	{
		em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		
		try 
		{
			return (List<Zone>) em.createQuery("SELECT z FROM Zone z WHERE z.user.id = " + userId).getResultList();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}
