package com.transactionservice.transaction_service.service;

import com.transactionservice.transaction_service.dto.VendorRequest;
import com.transactionservice.transaction_service.dto.VendorResponse;
import com.transactionservice.transaction_service.model.Vendor;
import com.transactionservice.transaction_service.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    public String uploadLogo(Long vendorId, MultipartFile file) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found with ID " + vendorId));

        try {
            byte[] logoBytes = file.getBytes();
            vendor.setLogo(logoBytes);
            vendorRepository.save(vendor);
            return "Logo uploaded successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload logo.";
        }
    }

    public VendorResponse getVendorDetails(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found with ID: " + vendorId));

        return mapVendorToResponse(vendor);
    }

//    public void addVendor(VendorRequest vendorRequest) {
//        Vendor vendor = vendorRepository.findById(vendorRequest.getVendorId()).orElse(null);
//
//        if (vendor != null) {
//            Vendor vendors = Vendor.builder()
//                    .id(vendorRequest.getVendorId())
//                    .VendorName(vendorRequest.getVendorName())
//                    .build();
//
//            vendorRepository.save(vendors);
//        } else {
//            System.out.println("No record found");
//        }
//    }

    public void addVendorWithLogo(MultipartFile file, String vendorName) {
        try {
            byte[] logoBytes = file.getBytes();
            Vendor vendor = Vendor.builder()
                    .vendorName(vendorName)
                    .logo(logoBytes)
                    .build();

            vendorRepository.save(vendor);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    private VendorResponse mapVendorToResponse(Vendor vendor) {
        return VendorResponse.builder()
                .id(vendor.getId())
                .logo(vendor.getLogo())
                .VendorName(vendor.getVendorName())
                .build();
    }
}
