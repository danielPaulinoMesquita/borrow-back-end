package com.borrow.borrowsecurity.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private List<Product> products;

    @BeforeEach
    public void setup(){
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

        products = Arrays.asList(product1, product2);
    }

    @Test
    public void getProductsTest(){
        /* todo to improve this test */
        when(productRepository.findAll()).thenReturn(this.products);

        List<Product> products = productService.getProducts();
        products.forEach(product -> {
                Assertions.assertTrue(this.products
                        .stream()
                        .anyMatch(product1 -> Objects.equals(product1.getId(), product.getId())));
        });
    }

    @Test
    public void getProductTest() {
        int productId = 1;

        when(productRepository
                .findById(productId))
                .thenReturn(products
                            .stream()
                            .filter(product -> product.getId() == productId).findFirst());

        Product product = productService.getProduct(1);

        Assertions.assertTrue(products
                              .stream()
                              .anyMatch(p -> Objects.equals(p.getId(), product.getId())));
    }

    @Test
    public void saveProductTest(){
        Product product = this.products.stream().findFirst().orElseThrow();

        when(productRepository.save(product)).thenReturn(product);

        Product productSaved = productService.saveProduct(product);

        Assertions.assertEquals(productSaved, product);
    }







}
