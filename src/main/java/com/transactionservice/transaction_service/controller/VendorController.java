package com.transactionservice.transaction_service.controller;
import com.transactionservice.transaction_service.dto.VendorRequest;
import com.transactionservice.transaction_service.dto.VendorResponse;
import com.transactionservice.transaction_service.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;
    @PostMapping("/{vendorId}/uploadLogo")
    public ResponseEntity<String> uploadLogo(@PathVariable Long vendorId, @RequestParam("file")MultipartFile file) {
        String message = vendorService.uploadLogo(vendorId, file);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/{vendorId}")
    public ResponseEntity<VendorResponse> getVendorDetails(@PathVariable Long vendorId){
        VendorResponse vendorDetails = vendorService.getVendorDetails(vendorId);
        return new ResponseEntity<>(vendorDetails, HttpStatus.OK);
    }
    @PostMapping("/addVendor")
    public ResponseEntity<VendorResponse> addVendor(
            @RequestParam("file") MultipartFile file,
            @RequestParam("vendorName") String vendorName) {
        vendorService.addVendorWithLogo(file, vendorName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
