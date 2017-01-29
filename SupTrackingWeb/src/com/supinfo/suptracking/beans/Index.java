package com.supinfo.suptracking.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Index
{
	private final String url = "https://www.google.com/maps/embed/v1/place?key=YOUR_GOOGLE_KEY";

	public String getUrl()
	{
		return url;
	}
}
