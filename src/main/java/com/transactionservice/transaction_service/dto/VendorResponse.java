package com.transactionservice.transaction_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorResponse {
    private Long id;
    private byte[] logo;
    private String VendorName;
}
