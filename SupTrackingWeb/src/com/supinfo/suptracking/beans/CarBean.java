package com.supinfo.suptracking.beans;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.supinfo.suptracking.beans.session.UserManager;
import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.Car;
import com.supinfo.suptracking.entities.GPSCar;
import com.supinfo.suptracking.globals.Alert;
import com.supinfo.suptracking.service.CarService;

@ManagedBean(name="carManager")
@ViewScoped
public class CarBean
{
	@EJB
	private CarService carService;
	
	@ManagedProperty(value="#{userManager}")
	private UserManager userManager;
	
	@ManagedProperty(value="#{car}")
	private Car car;
	
	public String add(String name, String brand, String year)
	{
		if(name != null && brand != null && year != null)
		{
			int yearFirstService = 0;
			try
			{
				yearFirstService = Integer.parseInt(year);
				
				if(yearFirstService > Calendar.getInstance().get(Calendar.YEAR) || yearFirstService < 1970)
					throw new NumberFormatException();
			}
			catch(NumberFormatException e)
			{
				((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Please enter a valid year.");
				return "addCar";
			}
			
			//Creation of a new Car
			Car car = new Car();
			car.setName(name);
			car.setBrand(brand);
			car.setYear(yearFirstService);
			car.setUser(getUserManager().getUser());
			car = carService.add(car);

			return "/auth/cars/cars?faces-redirect=true";
		}
		
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Please fill all the fields.");
		return "addCar";
	}
	
	public String modify(Long id, String name, String brand, String year)
	{
		if(name != null && brand != null && year != null)
		{			
            Car car = carService.getById(id);
            
			int yearFirstService = 0;
			try
			{
				yearFirstService = Integer.parseInt(year);
				
				if(yearFirstService > Calendar.getInstance().get(Calendar.YEAR) || yearFirstService < 1970)
					throw new NumberFormatException();
			}
			catch(NumberFormatException e)
			{
				((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Please enter a valid year.");
				return "addCar";
			}
            
            //Update of a car
        	car.setName(name);
        	car.setBrand(brand);
        	car.setYear(yearFirstService);
            
        	this.car = car;
        	
            carService.update(car);
		}
		
		return "/auth/cars/cars?faces-redirect=true";
	}

	
	public String goToGpsCarDetails(long id)
	{
		return "/auth/cars/carEntries?faces-redirect=true&id=" + id;
	}
	
	public String goToGpsModifyCar(long id)
	{
		return "/auth/cars/modifyCar?faces-redirect=true&id=" + id;
	}
	
	public String deleteCar(long id)
	{
		carService.remove(id);
		
		return "/auth/cars/cars?faces-redirect=true";
	}
	
	public boolean getCarById(String id)
	{
		long carId;
		try
		{
			carId = Long.parseLong(id);
		}
		catch(NumberFormatException e)
		{
			((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Do not hack.");
			return false;
		}
		
		this.car = carService.getById(carId);
		return this.car != null;
	}
	
	
	public UserManager getUserManager()
	{
		return userManager;
	}

	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}

	
	public List<GPSCar> getGpsEntries(long id)
	{
		return DaoFactory.getGPSCarDao().getTenLastEntriesOfACar(id);
	}

	public String getLastGpsEntries(long id)
	{
		String coord = "";
		if(DaoFactory.getGPSCarDao().getTenLastEntriesOfACar(id).size() != 0)
		{
			GPSCar gps = DaoFactory.getGPSCarDao().getTenLastEntriesOfACar(id).get(0);
			coord = gps.getLatitude() + "," + gps.getLongitude();
		}

		return coord;
	}
	
	public List<Car> getAllUserCars()
	{
		return carService.getAllByUser(getUserManager().getUser().getId());
	}
	
	public int CarsStats()
	{
		return carService.getAll().size();
	}
	
	public Car getCar()
	{
		return this.car;
	}
	public void setCar(Car car)
	{
		this.car = car;
	}
}
