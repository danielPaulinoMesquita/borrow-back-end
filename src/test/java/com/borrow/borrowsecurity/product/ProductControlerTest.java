package com.borrow.borrowsecurity.product;

import com.borrow.borrowsecurity.config.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControlerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public JwtService jwtService;

    @MockBean
    public ProductService productService;

    @WithMockUser
    @Test
    public void getProductTest() throws Exception {
        Product product = Product.builder()
                                 .id(1L)
                                 .type("COMPANY")
                                 .days("2")
                                 .description("Produto teste")
                                 .value("200")
                                 .build();

        when(productService.getProduct(product.getId())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                .param("id", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.type").value(product.getType()))
                .andExpect(jsonPath("$.days").value(product.getDays()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.value").value(product.getValue()));

    }
}
