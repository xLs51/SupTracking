package com.supinfo.suptracking.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
public class InfoViewer
{	
	private final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	public void removeErrorAttribute()
	{
		session.removeAttribute("errorMsg");
	}
	
	public void removeWarningAttribute()
	{
		session.removeAttribute("warningMsg");
	}
	
	public void removeInfoAttribute()
	{
		session.removeAttribute("infoMsg");
	}
	
	public void removeSuccessAttribute()
	{
		session.removeAttribute("successMsg");
	}
}
