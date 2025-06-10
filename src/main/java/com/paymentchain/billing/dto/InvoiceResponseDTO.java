package com.paymentchain.billing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "InvoiceResponse", description = "Model represent a invoice on database")
public class InvoiceResponseDTO {

    @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2", defaultValue = "1",
            description = "Unique Id of invoice")
    private long invoiceId;

    @Schema(name = "customer", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2", defaultValue = "1",
            description = "Unique Id of customer that represents the owner of invoice")
    private long customer;

    @Schema(name = "number", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "3", defaultValue = "8",
            description = "Number given on fisical invoice")
    private String number;

    private String detail;

    private double amount;
}
