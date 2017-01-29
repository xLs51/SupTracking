package com.supinfo.suptracking.service;

import java.util.List;

import com.supinfo.suptracking.entities.Invoice;

public interface InvoiceService
{
	public List<Invoice> getAll();
	public Invoice getSelectedInvoice(int invoiceId);
}
