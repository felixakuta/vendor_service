package com.transactionservice.transaction_service.repository;

import com.transactionservice.transaction_service.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
