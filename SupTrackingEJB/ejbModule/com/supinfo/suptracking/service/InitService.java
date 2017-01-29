package com.supinfo.suptracking.service;

import javax.ejb.Local;

@Local
public interface InitService
{
	public void initInvoices();
	public void initUsers();
	public void initCars();
	public void initGPS();
}
