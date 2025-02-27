package com.paymentchain.billing.service;

import com.paymentchain.billing.entities.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    public List<Invoice> getInvoices();
    public Optional<Invoice> getInvoice(long id);
    public Invoice createInvoice(Invoice invoice);
    public Optional<Invoice> updateInvoice(Invoice invoice);
    public Optional<Invoice> deleteInvoice(long id);
    
}
