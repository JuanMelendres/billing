package com.paymentchain.billing.mapstruct;

import com.paymentchain.billing.dto.InvoiceResponseDTO;
import com.paymentchain.billing.entities.Invoice;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface InvoiceResponseMapper {

    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "id", target = "invoiceId")
    InvoiceResponseDTO mapInvoiceToInvoiceResponseDTO(Invoice invoice);

    List<InvoiceResponseDTO> mapInvoiceToInvoiceResponseDTO(List<Invoice> invoiceList);

    @Mapping(source = "invoiceId", target = "id")
    @Mapping(source = "customer", target = "customerId")
    Invoice mapInvoiceResponseDTOtoInvoice(InvoiceResponseDTO dto);

    @Mapping(source = "invoiceId", target = "id")
    @Mapping(source = "customer", target = "customerId")
    List<Invoice> mapInvoiceResponseDTOsToInvoices(List<InvoiceResponseDTO> dtoList);
}
