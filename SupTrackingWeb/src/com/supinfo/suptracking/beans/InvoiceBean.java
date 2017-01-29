package com.supinfo.suptracking.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.supinfo.suptracking.entities.Invoice;
import com.supinfo.suptracking.service.InvoiceService;

@ManagedBean(name="invoiceManager")
public class InvoiceBean
{
	@EJB
	private InvoiceService invoiceService;
	
	public List<Invoice> getAll()
	{
		return invoiceService.getAll();
	}
	
	public int getCount()
	{
		return invoiceService.getAll().size();
	}
}
