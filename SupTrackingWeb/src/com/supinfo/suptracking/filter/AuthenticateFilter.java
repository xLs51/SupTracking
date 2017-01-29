package com.supinfo.suptracking.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.suptracking.beans.session.UserManager;

@WebFilter(urlPatterns="/faces/auth/*")
public class AuthenticateFilter implements Filter
{
	@Override
	public void destroy()
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		UserManager myAppBean = (UserManager) session.getAttribute("userManager");
		
		if(myAppBean != null && myAppBean.isLoggedIn()) 
			chain.doFilter(request, response);
		else
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/faces/login.xhtml");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{

	}
}
