package com.paymentchain.billing.service;

import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getInvoices() {
        log.info("Get Invoices");
        return this.invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> getInvoice(long id) {
        log.info("Get customer with id {}", id);

        return this.invoiceRepository.findById(id);
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        log.info("Creating new invoice: {}", invoice);

        return this.invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> updateInvoice(Invoice invoice) {
        Optional<Invoice> invoiceExist = this.invoiceRepository.findById(invoice.getId());
        if (invoiceExist.isPresent()) {

            log.info("Updating invoice with id " + invoice.getId());

            invoiceExist.get().setAmount(invoice.getAmount());
            invoiceExist.get().setDetail(invoice.getDetail());
            invoiceExist.get().setNumber(invoice.getNumber());

            this.invoiceRepository.save(invoiceExist.get());

            return invoiceExist;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Invoice> deleteInvoice(long id) {
        Optional<Invoice> invoiceExist = this.invoiceRepository.findById(id);

        if (invoiceExist.isPresent()) {
            log.info("Deleting invoice with id: " + id);
            log.info("Invoice: " + invoiceExist.get().toString());
            this.invoiceRepository.delete(invoiceExist.get());
        }

        return Optional.empty();
    }
}
