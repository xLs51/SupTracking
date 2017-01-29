package com.supinfo.suptracking.bean;

import java.util.List;

import javax.ejb.Stateless;

import com.supinfo.suptracking.dao.DaoFactory;
import com.supinfo.suptracking.entities.Invoice;
import com.supinfo.suptracking.service.InvoiceService;

@Stateless
public class InvoiceServiceBean implements InvoiceService
{
	@Override
	public List<Invoice> getAll()
	{
		return DaoFactory.getInvoiceDao().getAll();
	}

	@Override
	public Invoice getSelectedInvoice(int invoiceId)
	{
		return DaoFactory.getInvoiceDao().getById(invoiceId);
	}
}
