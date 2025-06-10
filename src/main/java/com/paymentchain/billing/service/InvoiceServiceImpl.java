package com.paymentchain.billing.service;

import com.paymentchain.billing.dto.InvoiceRequestDTO;
import com.paymentchain.billing.dto.InvoiceResponseDTO;
import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.mapstruct.InvoiceRequestMapper;
import com.paymentchain.billing.mapstruct.InvoiceResponseMapper;
import com.paymentchain.billing.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceRequestMapper invoiceRequestMapper;
    private final InvoiceResponseMapper invoiceResponseMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              InvoiceRequestMapper invoiceRequestMapper,
                              InvoiceResponseMapper invoiceResponseMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceRequestMapper = invoiceRequestMapper;
        this.invoiceResponseMapper = invoiceResponseMapper;
    }

    @Override
    public List<InvoiceResponseDTO> getInvoices() {

        List<Invoice> invoices = invoiceRepository.findAll();

        return invoiceResponseMapper.mapInvoiceToInvoiceResponseDTO(invoices);
    }

    @Override
    public Optional<InvoiceResponseDTO> getInvoice(long id) {
        log.info("Get customer with id {}", id);

        Invoice invoice = invoiceRepository.findById(id).orElse(null);

        if (invoice == null) {
            return Optional.empty();
        }

        InvoiceResponseDTO invoiceResponseDTO = invoiceResponseMapper.mapInvoiceToInvoiceResponseDTO(invoice);

        return Optional.of(invoiceResponseDTO);
    }

    @Override
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequest) {
        log.info("Creating new invoice: {}", invoiceRequest);

        Invoice invoice = invoiceRequestMapper.mapInvoiceRequestDTOtoInvoice(invoiceRequest);

        this.invoiceRepository.save(invoice);

        return invoiceResponseMapper.mapInvoiceToInvoiceResponseDTO(invoice);
    }

    @Override
    public Optional<InvoiceResponseDTO> updateInvoice(long id, InvoiceRequestDTO invoiceRequest) {

        Invoice invoiceExist = invoiceRepository.findById(id).orElse(null);

        if (invoiceExist == null) {
            return Optional.empty();
        }

        invoiceExist.setAmount(invoiceRequest.getAmount());
        invoiceExist.setDetail(invoiceRequest.getDetail());
        invoiceExist.setNumber(invoiceRequest.getNumber());

        this.invoiceRepository.save(invoiceExist);

        InvoiceResponseDTO invoiceResponseDTO = invoiceResponseMapper.mapInvoiceToInvoiceResponseDTO(invoiceExist);

        return Optional.of(invoiceResponseDTO);

    }

    @Override
    public Optional<InvoiceResponseDTO> deleteInvoice(long id) {
        Invoice invoiceExist = invoiceRepository.findById(id).orElse(null);

        if (invoiceExist == null) {
            return Optional.empty();
        }

        log.info("Deleting invoice with id: " + id);
        log.info("Invoice: " + invoiceExist);
        
        this.invoiceRepository.delete(invoiceExist);

        InvoiceResponseDTO invoiceResponseDTO = invoiceResponseMapper.mapInvoiceToInvoiceResponseDTO(invoiceExist);

        return Optional.of(invoiceResponseDTO);
    }
}
