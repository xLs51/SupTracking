package com.supinfo.suptracking.beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.supinfo.suptracking.beans.session.UserManager;
import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.Car;
import com.supinfo.suptracking.entities.GPSZone;
import com.supinfo.suptracking.entities.Zone;
import com.supinfo.suptracking.globals.Alert;
import com.supinfo.suptracking.service.CarService;
import com.supinfo.suptracking.service.GPSZoneService;
import com.supinfo.suptracking.service.ZoneService;

@ManagedBean(name="zoneManager")
@ViewScoped
public class ZoneBean
{	
	@EJB
	private ZoneService zoneService;
	
	@EJB
	private GPSZoneService gpsZoneService;
	
	@EJB
	private CarService carService;
	
	@ManagedProperty(value="#{userManager}")
	private UserManager userManager;
	
	private Zone zone;

	public String add(String firstTime, String secondTime, String latitude, String longitude, String radius, String str_car)
	{
		if(firstTime != null && secondTime != null && latitude != null  && longitude != null  && radius != null && str_car != null)
		{
			double lat = 0;
			double lon = 0;
			double rad = 0;
			long id_car = 0;
			
			try
			{
				lat = Double.parseDouble(latitude);
				lon = Double.parseDouble(longitude);
				rad = Double.parseDouble(radius);
				id_car = Long.parseLong(str_car);
			}
			catch(NumberFormatException e)
			{
				((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Please enter a valid coordinate.");
				return "addZone";
			}
						
			Date first = new Date();
			Date second = new Date();
			
			try
			{
				DateFormat f = new SimpleDateFormat("hh:mm");
				first = f.parse(firstTime);
				second = f.parse(secondTime);
			}
			catch(ParseException e)
			{
				((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Please enter a valid time.");
				return "addZone";
			}
			
			Car car = carService.getById(id_car);
			
			//Creation of a new Zone
			Zone zone = new Zone();
			zone.setFirstTime(first);
			zone.setSecondTime(second);
			zone.setRadius(rad);
			zone.setUser(getUserManager().getUser());
			zone.setCar(car);
			zoneService.add(zone);
			
			GPSZone gpsZone = new GPSZone();
			gpsZone.setLatitude(lat);
			gpsZone.setLongitude(lon);
			gpsZone.setZone_fk(zone);
			gpsZoneService.add(gpsZone);

			return "/auth/zones/zones?faces-redirect=true";
		}
		
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Please fill all the fields.");
		return "addZone";
	}
	
	public String modify(Long id, String firstTime, String secondTime, String radius, long carId)
	{
		if(firstTime != null && secondTime != null && radius != null)
		{
			double rad = 0;
			
			try
            {
                rad = Double.parseDouble(radius);
            }
            catch(NumberFormatException e)
            {
                ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Please enter a valid coordinate.");
                return "/auth/zones/modifyZone?faces-redirect=true&id=" + id;
            }
			
			Date first = new Date();
            Date second = new Date();

            try
            {
                DateFormat f = new SimpleDateFormat("EEE MMM d HH:mm:ss ZZZ yyyy", Locale.US);
                first = f.parse(firstTime);
                second = f.parse(secondTime);
            }
            catch(ParseException e)
            {
                ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Please enter a valid time.");
                return "/auth/zones/modifyZone?faces-redirect=true&id=" + id;
            }

            Car car = carService.getById(carId);
            
            //Update of a new Zone
            Zone zone = zoneService.getById(id);
            zone.setFirstTime(first);
            zone.setSecondTime(second);
            zone.setRadius(rad);
            zone.setCar(car);
            
            zoneService.update(zone);
		}
		
		return "/auth/zones/zones?faces-redirect=true";
	}
	
	public String remove(Long id)
	{
		Zone zone = zoneService.getById(id);
		
		if(zone.getUser().getId() == getUserManager().getUser().getId()) {			
			GPSZone gps = gpsZoneService.getByZone(id);
			gpsZoneService.remove(gps.getId());
			
			zoneService.remove(id);

			return "/auth/zones/zones?faces-redirect=true";
		}
		
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "You don't own this zone.");
		return "auth/zones/zones.xhtml";
	}
	
	public UserManager getUserManager()
	{
		return userManager;
	}

	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}

	public List<Zone> getAllUserZones()
	{
		return zoneService.getAllByUser(getUserManager().getUser().getId());
	}
	
	public String goToGpsZoneDetails(long id)
	{
		return "/auth/zones/zoneEntries?faces-redirect=true&id=" + id;
	}
	
	public String goToGpsModifyZone(long id)
	{
		return "/auth/zones/modifyZone?faces-redirect=true&id=" + id;
	}
	
	public List<GPSZone> getGpsEntries(long id)
	{
		return DaoFactory.getGPSZoneDao().getTenLastEntriesOfAZone(id);
	}
	
	public Zone getZone()
	{
		return this.zone;
	}
	
	public void setZone(Zone zone)
	{
		this.zone = zone;
	}
	
	public Zone getZone(long id)
	{
		return this.zone = zoneService.getById(id);
	}
}
