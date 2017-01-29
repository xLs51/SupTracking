package com.supinfo.suptracking.soap;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.service.LoginService;

@WebService(name="UserHandlerService", serviceName="UserHandler")
public class UserHandlerWebService
{
	@EJB
	private LoginService loginService;
	
	@WebMethod(operationName="authenticate")
	public User authenticate(@WebParam(name="username") String username, @WebParam(name="password") String password)
	{
		return loginService.connect(username, password);
	}
}
