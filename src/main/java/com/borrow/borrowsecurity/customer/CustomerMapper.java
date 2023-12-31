package com.borrow.borrowsecurity.customer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;

    public CustomerMapper(){
        modelMapper = new ModelMapper();
    }

    public Customer mapCustomerRequestToCustomer(CustomerRequest customerRequest){
        return modelMapper.map(customerRequest, Customer.class);
    }

    public CustomerResponse mapCustomerToCustomerResponse(Customer customer){
        return modelMapper.map(customer, CustomerResponse.class);
    }

}
