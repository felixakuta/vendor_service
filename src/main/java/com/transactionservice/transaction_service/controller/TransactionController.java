package com.transactionservice.transaction_service.controller;

import com.transactionservice.transaction_service.dto.TransactionRequest;
import com.transactionservice.transaction_service.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.transactionservice.transaction_service.service.TransactionService;

import java.util.List;

@RequestMapping("/api/transactions")
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{driverId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByDriverId(@PathVariable Long driverId){
        List<TransactionResponse> transactions = transactionService.getTransactionsByDriverId(driverId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequest transactionRequest){
        transactionService.addTransaction(transactionRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        String transactions = transactionService.uploadFile(file);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
