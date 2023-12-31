package com.borrow.borrowsecurity.customer;

import com.borrow.borrowsecurity.user.Role;
import com.borrow.borrowsecurity.user.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

// todo to verify if it is possible to change for dto
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String name;
    private String document;
    private String description;
    List<Role> roles;

//    fixme take out later
    public CustomerResponse mapUserToCustomerResponse(User user) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setName(user.getName());
        customerResponse.setRoles(user.getAuthorities()
                .stream()
                .map(role -> Role.valueOf(role.getAuthority())).collect(Collectors.toList()));
        return customerResponse;
    }

}
