package com.borrow.borrowsecurity.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getProductsTest(){
        Product product1 = Product.builder()
                .id(1)
                .days("1")
                .value("100,00")
                .type("FERRAMENTA")
                .description("Cut").build();

        Product product2 = Product.builder()
                .id(2)
                .days("1")
                .value("100,00")
                .type("UTE")
                .description("Cut").build();

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getProducts();
        boolean res = products.stream().anyMatch(product -> Objects.equals(product.getId(), product1.getId()));
        Assertions.assertTrue(res);
    }

}
