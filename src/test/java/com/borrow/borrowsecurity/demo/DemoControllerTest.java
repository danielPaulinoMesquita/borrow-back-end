package com.borrow.borrowsecurity.demo;

import com.borrow.borrowsecurity.config.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(DemoController.class)
@ExtendWith(MockitoExtension.class)
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    //https://www.youtube.com/watch?v=XASyDbfQYaw&t=725s fixme

    /*
    This annotation @WithMockUser is extremely important to run the tests with security layer
     */
    @WithMockUser(value = "spring")
    @Test
    public void sayHelloTest() throws Exception
    {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/demo-controller")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Assertions.assertEquals("Hello From secutiry and DemoController",
                result.getResponse().getContentAsString());


    }

}
