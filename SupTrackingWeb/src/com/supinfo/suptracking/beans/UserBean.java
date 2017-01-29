package com.supinfo.suptracking.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.supinfo.suptracking.beans.session.UserManager;
import com.supinfo.suptracking.entities.User;
import com.supinfo.suptracking.globals.Alert;
import com.supinfo.suptracking.globals.RegisterConstraints;
import com.supinfo.suptracking.service.InvoiceService;
import com.supinfo.suptracking.service.RegisterService;
import com.supinfo.suptracking.service.UserService;
import com.supinfo.suptracking.utils.EncryptionServices;
import com.supinfo.suptracking.utils.PatternValidator;

@ManagedBean(name="user")
@ViewScoped
public class UserBean
{
	@EJB
	private UserService userService;
	
	@EJB
	private RegisterService registerService;
	
	@EJB
	private InvoiceService invoiceService;
	
	@ManagedProperty(value="#{userManager}")
	private UserManager userManager;
	
	private User user;
	
	public int UsersStats()
	{
		return userService.getAll().size();
	}
	
	public List<User> getAllUsers()
	{
		return userService.getAll();
	}
	
	public User getUser(long id)
	{
		return this.user = userService.getById(id);
	}

	public User getUser()
	{
		return this.user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public String goToModify(long id)
	{
		return "/auth/profile/editProfile?faces-redirect=true&id=" + id;
	}
	
	public UserManager getUserManager()
	{
		return userManager;
	}
	
	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}
	
	public String remove(Long id)
	{
		if(getUserManager().getUser().getIsAdmin() == true)
		{
			userService.remove(id);
			return "/auth/admin/dashboard?faces-redirect=true";
		}
		
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "You're not allowed to delete this user.");
		return "index.xhtml";
	}
	
	public String modifyByAdmin(long id, String email, String passwordNew, String passwordConfirmation, String firstname, String lastname, String username, String phone, String address, String creditCard/*, String invoice*/)
	{
		if(userManager.getUser().getIsAdmin())
		{
			if(email != null && firstname != null && lastname != null 
					&& phone != null && address != null && creditCard != null)
			{

				if(!registerService.isMailExisting(email) || email.equals(getUser(id).getMail()))
				{
					if(!registerService.isPhoneNumberExisting(phone) || phone.equals(getUser(id).getPhone()))
					{
						User updatedUser = getUser(id);
										
						if(!passwordNew.isEmpty() && !passwordConfirmation.isEmpty() )
						{					
							if(PatternValidator.isPasswordValid(passwordNew) && passwordNew.equals(passwordConfirmation))
							{
								updatedUser.setPassword(EncryptionServices.encryptSHA1(passwordNew));
							}
							else
								((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your credentials are incorrect. Please verify that passwords match and are at least "+ RegisterConstraints.PASSWORD_LENGTH  +" caracters long.");
						}
						
						if(PatternValidator.isPhoneNumberValid(phone.replace("-", "").replace(",", "")))
						{
							if(PatternValidator.isCreditCardNumberValid(creditCard.replace("-", "").replace(",", "")))
							{																
								updatedUser.setFirstName(firstname);
								updatedUser.setLastName(lastname);
								updatedUser.setCreditCard(creditCard);
								updatedUser.setPhone(phone);
								updatedUser.setMail(email);
								updatedUser.setAdress(address);
								userService.update(updatedUser);
			
								return "/auth/admin/dashboard?faces-redirect=true";
							}
							else
								((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your credit card is not valid. Check it out!");
						}
						else
							((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your phone number is not valid. Check it out!");						
					}
					else
						((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "This phone number already exists.");
				}
				else
					((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "This mail already exists."); 
			}
		}
		return "index";
	}
	
	public String modifyByUser(long id, String email, String passwordOld, String passwordNew, String passwordConfirmation, String firstname, String lastname, String username, String phone, String address, String creditCard/*, String invoice*/)
	{
		if(userManager.getUser().getId() == id)
		{
			if(email != null && firstname != null && lastname != null 
					&& phone != null && address != null && creditCard != null)
			{				
				if(!registerService.isMailExisting(email) || email.equals(getUser(id).getMail()))
				{
					if(!registerService.isPhoneNumberExisting(phone) || phone.equals(getUser(id).getPhone()))
					{
						User updatedUser = getUser(id);
												
						if(!passwordOld.isEmpty() && !passwordNew.isEmpty() && !passwordConfirmation.isEmpty() )
						{
							String encryptedPass = EncryptionServices.encryptSHA1(passwordOld);
							if(encryptedPass.equals(getUser(id).getPassword()))
							{														
								if(PatternValidator.isPasswordValid(passwordNew) && passwordNew.equals(passwordConfirmation))
								{
									updatedUser.setPassword(EncryptionServices.encryptSHA1(passwordNew));
								}
								else
									((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your credentials are incorrect. Please verify that passwords match and are at least "+ RegisterConstraints.PASSWORD_LENGTH  +" caracters long.");
							}
							else
								((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your credentials are incorrect. Please verify the old password you entered.");
						}
						
						if(PatternValidator.isPhoneNumberValid(phone.replace("-", "").replace(",", "")))
						{
							if(PatternValidator.isCreditCardNumberValid(creditCard.replace("-", "").replace(",", "")))
							{																	
								updatedUser.setFirstName(firstname);
								updatedUser.setLastName(lastname);
								updatedUser.setCreditCard(creditCard);
								updatedUser.setPhone(phone);
								updatedUser.setMail(email);
								updatedUser.setAdress(address);
								userService.update(updatedUser);
			
								//Updating the new User in "session"
								getUserManager().setUser(updatedUser);
								return "/auth/profile/profile?faces-redirect=true";
							}
							else
								((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your credit card is not valid. Check it out!");
						}
						else
							((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "Your phone number is not valid. Check it out!");						
					}
					else
						((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "This phone number already exists.");
				}
				else
					((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(Alert.PRMTR_ERROR, "This mail already exists.");
			}
		}
		
		return "index";
	}
}
