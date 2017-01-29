package com.supinfo.suptracking.dao;

import com.supinfo.suptracking.dao.jpa.JpaCarDao;
import com.supinfo.suptracking.dao.jpa.JpaGPSCarDao;
import com.supinfo.suptracking.dao.jpa.JpaGPSUserDao;
import com.supinfo.suptracking.dao.jpa.JpaGPSZoneDao;
import com.supinfo.suptracking.dao.jpa.JpaInvoiceDao;
import com.supinfo.suptracking.dao.jpa.JpaUserDao;
import com.supinfo.suptracking.dao.jpa.JpaZoneDao;

public class DaoFactory
{
	private DaoFactory()
	{
		
	}
	
	public static UserDao getUserDao()
	{
		return new JpaUserDao();
	}
	
	public static InvoiceDao getInvoiceDao()
	{
		return new JpaInvoiceDao();
	}
	
	public static CarDao getCarDao()
	{
		return new JpaCarDao();
	}
	
	public static GPSCarDao getGPSCarDao()
	{
		return new JpaGPSCarDao();
	}
	
	public static GPSUserDao getGPSUserDao()
	{
		return new JpaGPSUserDao();
	}
	
	public static ZoneDao getZoneDao()
	{
		return new JpaZoneDao();
	}
	
	public static GPSZoneDao getGPSZoneDao()
	{
		return new JpaGPSZoneDao();
	}
}
