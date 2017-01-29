package com.supinfo.suptracking.dao.jpa;

import com.supinfo.suptracking.dao.InvoiceDao;
import com.supinfo.suptracking.entities.Invoice;

public class JpaInvoiceDao extends AbstractJpaDao<Invoice> implements InvoiceDao 
{
	public JpaInvoiceDao() 
	{
		super(Invoice.class);		
	}
}