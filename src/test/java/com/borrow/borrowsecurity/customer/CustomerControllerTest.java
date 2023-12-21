package com.borrow.borrowsecurity.customer;

import com.borrow.borrowsecurity.config.JwtService;
import com.borrow.borrowsecurity.user.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomerService customerService;

    @WithMockUser
    @Test
    public void getBasicInfoTest () throws Exception
    {
        String documentNumber = "11111111133",
                nameTest = "testRespCustomer";

        CustomerResponse response = CustomerResponse.builder()
                                                    .name(nameTest)
                                                    .roles(List.of(Role.CUSTOMER))
                                                    .build();


        when(customerService.getRoles(documentNumber)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/customer/info/{documentNumber}", documentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value(nameTest));

    }
}





