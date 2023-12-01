package com.transactionservice.transaction_service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.transactionservice.transaction_service.model.Driver;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponse {
    private Long id;
    private Driver driver;
    private Double amount;
    private String description;
    private LocalDateTime timestamp;
}

