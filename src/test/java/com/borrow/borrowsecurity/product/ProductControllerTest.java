package com.borrow.borrowsecurity.product;

import com.borrow.borrowsecurity.config.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public JwtService jwtService;

    @MockBean
    public ProductService productService;

    @MockBean
    public ProductMapper productMapper;

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

    @WithMockUser
    @Test
    public void getProductsTest() throws Exception {
        Product product = Product.builder()
                                 .id(1L)
                                 .type("COMPANY")
                                 .days("2")
                                 .description("Produto teste")
                                 .value("200")
                                 .build();

        Product product1 = Product.builder()
                                 .id(2L)
                                 .type("CLIENT")
                                 .days("3")
                                 .description("Produto teste")
                                 .value("300")
                                 .build();

        when(productService.getProducts()).thenReturn(Arrays.asList(product, product1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(product.getId()))
                .andExpect(jsonPath("$[0].type").value(product.getType()))
                .andExpect(jsonPath("$[0].days").value(product.getDays()))
                .andExpect(jsonPath("$[0].description").value(product.getDescription()))
                .andExpect(jsonPath("$[0].value").value(product.getValue()))
                .andExpect(jsonPath("$[1].id").value(product1.getId()))
                .andExpect(jsonPath("$[1].type").value(product1.getType()))
                .andExpect(jsonPath("$[1].days").value(product1.getDays()))
                .andExpect(jsonPath("$[1].description").value(product1.getDescription()))
                .andExpect(jsonPath("$[1].value").value(product1.getValue()));

    }

    @WithMockUser
    @Test
    public void saveTest() throws Exception {
        Product product = Product.builder()
                                 .id(1L)
                                 .type("COMPANY")
                                 .days("2")
                                 .description("Produto teste")
                                 .value("200")
                                 .build();

        ProductRequest productRequest = ProductRequest.builder()
                                                      .type("COMPANY")
                                                      .days("2")
                                                      .description("Produto teste")
                                                      .value("200")
                                                       .customer_id(1L)
                                                      .build();

        when(productService.saveProduct(productRequest)).thenReturn(product);
        when(productMapper.productRequestToProduct(productRequest)).thenReturn(product);

        ObjectMapper objectMapper = new ObjectMapper();

        String requestBody = objectMapper.writeValueAsString(productRequest);

        System.out.println(requestBody);

        // happened a problem of security 403, and the solution was:
        // https://medium.com/@kjavaman12/how-to-fix-an-error-403-with-mockmvc-and-junit-609f88b37c40

        mockMvc.perform(post("/api/v1/products/save-product")
                        .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(product.getType()))
                .andExpect(jsonPath("$.days").value(product.getDays()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.value").value(product.getValue()));

    }
}
