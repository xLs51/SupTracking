package com.supinfo.suptracking.listener;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.suptracking.service.InitService;
import com.supinfo.suptracking.utils.PersistenceManager;

@WebListener
public class PersistenceAppListener implements ServletContextListener
{
	@EJB
	private InitService initService;
	
	public PersistenceAppListener()
	{

	}

	public void contextInitialized(ServletContextEvent arg0)
	{
		initService.initInvoices();
		initService.initUsers();
		initService.initCars();
		initService.initGPS();
	}

	public void contextDestroyed(ServletContextEvent arg0)
	{
		PersistenceManager.closeEntityManagerFactory();
	}
}
