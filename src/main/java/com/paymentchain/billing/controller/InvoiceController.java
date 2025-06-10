package com.paymentchain.billing.controller;

import com.paymentchain.billing.dto.InvoiceRequestDTO;
import com.paymentchain.billing.dto.InvoiceResponseDTO;
import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.service.InvoiceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Billing API", description = "This API serve all functionality for management Invoices")
@Slf4j
@RestController
@RequestMapping("/v1/invoice")
public class InvoiceController {

    private final InvoiceServiceImpl invoiceService;

    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(description = "Return all transaction bundled into Response",
        summary = "Return 202 if no data found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    public ResponseEntity<List<InvoiceResponseDTO>> getInvoice() {
        try {
            List<InvoiceResponseDTO> customers = this.invoiceService.getInvoices();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoice(@PathVariable long id) {
        log.info("Get invoice with id {}", id);
        try {
            Optional<InvoiceResponseDTO> invoiceExist = this.invoiceService.getInvoice(id);
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
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody InvoiceRequestDTO invoice) {
        try {
            InvoiceResponseDTO newInvoice = this.invoiceService.createInvoice(invoice);
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> updateInvoice(long id, @RequestBody InvoiceRequestDTO invoice) {
        log.info("Update invoice with id {}", id);
        try {
            Optional<InvoiceResponseDTO> invoiceExist = this.invoiceService.updateInvoice(id, invoice);
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
    public ResponseEntity<InvoiceResponseDTO> deleteInvoice(@PathVariable long id) {
        log.info("Delete invoice with id {}", id);
        try {
            Optional<InvoiceResponseDTO> invoiceExist = this.invoiceService.deleteInvoice(id);
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