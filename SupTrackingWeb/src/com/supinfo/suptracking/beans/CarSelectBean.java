package com.supinfo.suptracking.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import com.supinfo.suptracking.beans.session.UserManager;
import com.supinfo.suptracking.entities.Car;
import com.supinfo.suptracking.service.CarService;

@ManagedBean(name="carSelectManager")
public class CarSelectBean
{
	@EJB
	private CarService carService;
	
	@ManagedProperty(value="#{userManager}")
	private UserManager userManager;
	
	private String selectedItem;
	private List<SelectItem> availableItems;
	
	@PostConstruct
	public void init()
	{
	    availableItems = new ArrayList<SelectItem>();
	    List<Car> cars = carService.getAllByUser(getUserManager().getUser().getId());
	    for (Car car : cars) {
	    	availableItems.add(new SelectItem(car.getId(), car.getName()));
		}
	}	
	
	public UserManager getUserManager()
	{
		return userManager;
	}

	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}

	public String getSelectedItem()
	{
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem)
	{
		this.selectedItem = selectedItem;
	}

	public List<SelectItem> getAvailableItems()
	{
		return availableItems;
	}
}
