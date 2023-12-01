package com.transactionservice.transaction_service.service;

import com.transactionservice.transaction_service.dto.DriverRequest;
import com.transactionservice.transaction_service.dto.DriverResponse;
import com.transactionservice.transaction_service.model.Driver;
import com.transactionservice.transaction_service.repository.DriverRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    public DriverResponse addDriver(DriverRequest driverRequest){

        if(driverRepository.existsByName(driverRequest.getName())){
            throw new IllegalArgumentException("Driver with the given name already exist");
        }

        Driver drivers = new Driver();
        drivers.setName(driverRequest.getName());
        Driver savedDriver = driverRepository.save(drivers);
        return mapDriverToResponse(savedDriver);
    }
    private DriverResponse mapDriverToResponse (Driver driver){
        return DriverResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .build();
    }
    public DriverResponse getDriverById(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with ID: " + driverId));
        return mapDriverToResponse(driver);
    }


}
