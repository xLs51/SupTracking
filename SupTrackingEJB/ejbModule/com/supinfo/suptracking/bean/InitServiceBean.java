package com.supinfo.suptracking.bean;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.Car;
import com.supinfo.suptracking.entities.GPSCar;
import com.supinfo.suptracking.entities.Invoice;
import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.service.InitService;
import com.supinfo.suptracking.utils.EncryptionServices;

@Stateless
public class InitServiceBean implements InitService
{
	@Override
	public void initInvoices()
	{
		if(DaoFactory.getInvoiceDao().getAll().size() == 0)
		{
			Invoice firstInvoice = new Invoice();
			firstInvoice.setName("Basic Plan");
			firstInvoice.setPrice(10);
			firstInvoice.setDuration(30);

			DaoFactory.getInvoiceDao().add(firstInvoice);
		}
	}
	
	@Override
	public void initUsers()
	{
		if(DaoFactory.getUserDao().getAll().size() == 0)
		{
			User user = new User();
			user.setUsername("User");
			user.setPassword(EncryptionServices.encryptSHA1("123456789"));
			user.setFirstName("User");
			user.setLastName("User");
			user.setCreditCard("4929384493122038");
			user.setPhone("0606060607");
			user.setMail("user@supinfo.com");
			user.setAdress("1 rue des Users");
			user.setInvoice(DaoFactory.getInvoiceDao().getAll().get(0));

			DaoFactory.getUserDao().add(user);
			
			User admin = new User();
			admin.setUsername("Admin");
			admin.setPassword(EncryptionServices.encryptSHA1("123456789"));
			admin.setFirstName("Admin");
			admin.setLastName("Admin");
			admin.setCreditCard("4929384493122038");
			admin.setPhone("0606060606");
			admin.setMail("admin@supinfo.com");
			admin.setAdress("1 rue des Admins");
			admin.setInvoice(DaoFactory.getInvoiceDao().getAll().get(0));
			admin.setAdmin(true);

			DaoFactory.getUserDao().add(admin);
		}
	}
	
	@Override
	public void initCars()
	{
		if(DaoFactory.getCarDao().getAll().size() == 0)
		{
			Car car = new Car();
			car.setName("Voiture 1");
			car.setBrand("Ferrari");
			car.setYear(2015);
			car.setUser(DaoFactory.getUserDao().getAll().get(0));

			DaoFactory.getCarDao().add(car);
		}
	}
	
	@Override
	public void initGPS()
	{
		if(DaoFactory.getGPSCarDao().getAll().size() == 0)
		{
			GPSCar gps = new GPSCar();
			gps.setLatitude(49.25);
			gps.setLongitude(4.033333);
			gps.setCar_fk(DaoFactory.getCarDao().getAll().get(0));

			DaoFactory.getGPSCarDao().add(gps);
		}
	}
}