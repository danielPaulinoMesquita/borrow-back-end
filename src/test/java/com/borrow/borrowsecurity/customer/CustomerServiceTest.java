package com.borrow.borrowsecurity.customer;

import com.borrow.borrowsecurity.user.Role;
import com.borrow.borrowsecurity.user.User;
import com.borrow.borrowsecurity.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void getRolesTest() {
        String documentNumber = "11111111133";

        User userTest = User.builder()
                            .id(123)
                            .name("test")
                            .documentNumber(documentNumber)
                            .segment("CUSTOMER")
                            .email("test@gmail.com")
                            .role(Role.CUSTOMER)
                            .password("123").build();

        when(userRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.ofNullable(userTest));

        CustomerResponse customerResponse = customerService.getRoles(documentNumber);

        Assertions.assertEquals(userTest.getName(), customerResponse.getName());

        customerResponse.getRoles().forEach(r -> {
            Assertions.assertEquals(userTest.getSegment(), r.name());
        });

    }
}
