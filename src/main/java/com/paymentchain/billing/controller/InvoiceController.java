package com.paymentchain.billing.controller;

import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.service.InvoiceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/invoice")
public class InvoiceController {

    private final InvoiceServiceImpl invoiceService;

    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping()
    public ResponseEntity<List<Invoice>> getInvoice() {
        try {
            List<Invoice> customers = this.invoiceService.getInvoices();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable long id) {
        log.info("Get invoice with id {}", id);
        try {
            Optional<Invoice> invoiceExist = this.invoiceService.getInvoice(id);
            return invoiceExist
                    .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        log.info("Create invoice {}", invoice.getId());
        try {
            Invoice newInvoice = this.invoiceService.createInvoice(invoice);
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(long id, @RequestBody Invoice invoice) {
        log.info("Update invoice with id {}", id);
        try {
            Optional<Invoice> invoiceExist = this.invoiceService.updateInvoice(id, invoice);
            return invoiceExist
                    .map(updatedInvoice -> new ResponseEntity<>(updatedInvoice, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable long id) {
        log.info("Delete invoice with id {}", id);
        try {
            Optional<Invoice> invoiceExist = this.invoiceService.deleteInvoice(id);
            return invoiceExist
                    .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}