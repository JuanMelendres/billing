package com.paymentchain.billing.mapstruct;

import com.paymentchain.billing.dto.InvoiceResponseDTO;
import com.paymentchain.billing.dto.InvoiceRequestDTO;
import com.paymentchain.billing.entities.Invoice;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface InvoiceResponseMapper {

    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "id", target = "invoiceId")
    InvoiceResponseDTO mapInvoiceToInvoiceResponseDTO(Invoice invoice);

    List<InvoiceResponseDTO> mapInvoiceToInvoiceResponseDTO(List<Invoice> invoice);

    @InheritInverseConfiguration
    Invoice mapInvoiceRequestDTOtoInvoice(InvoiceResponseDTO invoiceRequestDTO);

    @InheritInverseConfiguration
    List<Invoice> mapInvoiceRequestDTOtoInvoices(List<InvoiceRequestDTO> invoiceRequestDTO);

    Optional<InvoiceResponseDTO> mapInvoiceToInvoiceResponseDTO(Optional<Invoice> byId);
}
