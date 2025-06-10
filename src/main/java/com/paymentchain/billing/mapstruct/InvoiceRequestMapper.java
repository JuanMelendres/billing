package com.paymentchain.billing.mapstruct;

import com.paymentchain.billing.dto.InvoiceRequestDTO;
import com.paymentchain.billing.entities.Invoice;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface InvoiceRequestMapper {

    @Mapping(source = "customer", target = "customerId")
    @Mapping(target = "id", ignore = true)
    Invoice mapInvoiceRequestDTOtoInvoice(InvoiceRequestDTO invoiceRequestDTO);

    List<Invoice> mapInvoiceRequestDTOtoInvoices(List<InvoiceRequestDTO> invoiceRequestDTO);

    @InheritInverseConfiguration
    InvoiceRequestDTO mapInvoiceRequestDTOtoInvoice(Invoice invoice);

    @InheritInverseConfiguration
    List<InvoiceRequestDTO> mapInvoicesToInvoiceRequestDTO(List<Invoice> invoice);
}
