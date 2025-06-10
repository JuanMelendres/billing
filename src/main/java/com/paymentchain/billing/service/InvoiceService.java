package com.paymentchain.billing.service;

import com.paymentchain.billing.dto.InvoiceRequestDTO;
import com.paymentchain.billing.dto.InvoiceResponseDTO;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<InvoiceResponseDTO> getInvoices();
    Optional<InvoiceResponseDTO> getInvoice(long id);
    InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoice);
    Optional<InvoiceResponseDTO> updateInvoice(long id, InvoiceRequestDTO invoice);
    Optional<InvoiceResponseDTO> deleteInvoice(long id);
}
