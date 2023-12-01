package com.transactionservice.transaction_service.repository;

import com.transactionservice.transaction_service.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    boolean existsByName(String name);
}
