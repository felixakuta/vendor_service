package com.transactionservice.transaction_service.controller;

import com.transactionservice.transaction_service.dto.DriverRequest;
import com.transactionservice.transaction_service.dto.DriverResponse;
import com.transactionservice.transaction_service.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/add")
    public ResponseEntity<DriverResponse> addDriver(@RequestBody DriverRequest driverRequest) {
        DriverResponse newDriver = driverService.addDriver(driverRequest);
        return new ResponseEntity<>(newDriver, HttpStatus.CREATED );
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable Long driverId){
        DriverResponse driver = driverService.getDriverById(driverId);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }
}
