package com.borrow.borrowsecurity.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/info/{documentNumber}")
    public ResponseEntity<CustomerResponse> getBasicInfo (@PathVariable("documentNumber") String documentNumber){
        return ResponseEntity.ok(customerService.getRoles(documentNumber));
    }
}
