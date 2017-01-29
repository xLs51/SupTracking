package com.supinfo.suptracking.beans.session;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.supinfo.suptracking.entities.Invoice;
import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.globals.Alert;
import com.supinfo.suptracking.globals.RegisterConstraints;
import com.supinfo.suptracking.service.InvoiceService;
import com.supinfo.suptracking.service.LoginService;
import com.supinfo.suptracking.service.RegisterService;
import com.supinfo.suptracking.utils.EncryptionServices;
import com.supinfo.suptracking.utils.PatternValidator;

@ManagedBean(name="userManager")
@SessionScoped
public class UserManager
{
    private User current;

    @EJB
    private LoginService loginService;
    @EJB
    private RegisterService registerService;
    @EJB
    private InvoiceService invoiceService;

	public String logIn(String username, String password)
	{
		User user = loginService.connect(username, EncryptionServices.encryptSHA1(password));
		
		current = user;
		
		if(current != null)
			return "index?faces-redirect=true";
		else
			return "login";
	}

    public String logout()
    {
    	current = null;
        return "/index?faces-redirect=true";
    }

    public User getUser()
    {
    	return current;
    }
    
    public void setUser(User user)
    {
    	current = user;
    }
    
    public boolean isLoggedIn()
    {
        return current != null;
    }
    
	public String register(String email, String password, String passwordConfirmation, String firstname, String lastname, String username, String phone, String address, String creditCard, String invoice)
	{
		if(email != null && password != null && passwordConfirmation != null && firstname != null && lastname != null && username != null 
				&& phone != null && address != null && creditCard != null && invoice != null)
		{
			if(!registerService.isUserExisting(username))
			{
				if(!registerService.isMailExisting(email))
				{
					if(!registerService.isPhoneNumberExisting(phone))
					{
						if(PatternValidator.isPasswordValid(password) && password.equals(passwordConfirmation))
						{
							if(PatternValidator.isPhoneNumberValid(phone.replace("-", "").replace(",", "")))
							{
								if(PatternValidator.isCreditCardNumberValid(creditCard.replace("-", "").replace(",", "")))
								{
									Invoice selectedInvoice = invoiceService.getSelectedInvoice(Integer.parseInt(invoice));
									
									if(selectedInvoice != null)
									{
										//Creation of a new User
										User user = new User();
										user.setUsername(username);
										user.setPassword(EncryptionServices.encryptSHA1(password));
										user.setFirstName(firstname);
										user.setLastName(lastname);
										user.setCreditCard(creditCard);
										user.setPhone(phone);
										user.setMail(email);
										user.setAdress(address);
										user.setInvoice(selectedInvoice);
										user = registerService.registerUser(user);
					
										//Connecting the new User
										current = user;
										return "index?faces-redirect=true";
									}
									else
										((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Why are u hacking ?");
								}
								else
									((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your credit card is not valid. Check it out!");
							}
							else
								((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your phone number is not valid. Check it out!");
						}
						else
							((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your credentials are incorrect. Please verify that passwords match and are at least "+ RegisterConstraints.PASSWORD_LENGTH  +" caracters long.");
					}
					else
						((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "This phone number already exists.");
				}
				else
					((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "This mail already exists.");
			}
			else
				((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your credentials are incorrect. This username is already used."); 
		}
		
		return "register";
	}
}