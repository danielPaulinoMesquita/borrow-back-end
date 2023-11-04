package com.borrow.borrowsecurity.customer;

import com.borrow.borrowsecurity.user.User;
import com.borrow.borrowsecurity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final UserRepository userRepository;

    public CustomerResponse getRoles(String documentNumber) {
        User user = userRepository.findByDocumentNumber(documentNumber).orElseThrow(()-> new RuntimeException("Not Found"));
        return new CustomerResponse().mapUserToCustomerResponse(user);
    }

}
