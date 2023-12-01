package com.transactionservice.transaction_service.service;


import com.transactionservice.transaction_service.config.CloudinaryConfig;
import com.transactionservice.transaction_service.dto.TransactionRequest;
import com.transactionservice.transaction_service.dto.TransactionResponse;
import com.transactionservice.transaction_service.model.Driver;
import com.transactionservice.transaction_service.repository.DriverRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.transactionservice.transaction_service.model.Transaction;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.transactionservice.transaction_service.repository.TransactionRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

private final TransactionRepository transactionRepository;
private final DriverRepository driverRepository;
    public List<TransactionResponse> getTransactionsByDriverId(Long driverId){
        List<Transaction> transactions = transactionRepository.findDriverById(driverId);
        return transactions.stream()
                .map(this::mapTransactionToResponse)
                .collect(Collectors.toList());

    }
    public void addTransaction(TransactionRequest transactionRequest){
        Driver driver = driverRepository.findById(transactionRequest.getDriverId()).orElse(null);

        if(driver != null){
            Transaction transaction = Transaction.builder()
                    .driver(driver)
                    .amount(transactionRequest.getAmount())
                    .description(transactionRequest.getDescription())
                    .timestamp(LocalDateTime.now())
                    .build();
            transactionRepository.save(transaction);
        }
        else {
            System.out.println("No record found for the driver");
        }
    }

    private TransactionResponse mapTransactionToResponse(Transaction transaction){
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .timestamp(transaction.getTimestamp())
                .build();
    }
    public String uploadFile(MultipartFile file)  {
        CloudinaryConfig config = new CloudinaryConfig();
        try {

            return  config.upload(file, System.currentTimeMillis());
        }catch (IOException e){
            log.info("Exception{}",e.getMessage());
            return e.getMessage();
        }

    }

}
