package com.borrow.borrowsecurity.customer;

import com.borrow.borrowsecurity.user.Role;
import com.borrow.borrowsecurity.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

// todo To verify if it is possible to change for dto
@Data
public class CustomerResponse {
    String name;
    List<Role> roles;

    public CustomerResponse mapUserToCustomerResponse(User user) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setName(user.getName());
        customerResponse.setRoles(user.getAuthorities()
                .stream()
                .map(role -> Role.valueOf(role.getAuthority())).collect(Collectors.toList()));
        return customerResponse;
    }

}
