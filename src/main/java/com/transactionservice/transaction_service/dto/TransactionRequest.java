package com.transactionservice.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {
    private Long driverId;
    private Double amount;
    private String description;
}
