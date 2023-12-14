package com.borrow.borrowsecurity;

import com.borrow.borrowsecurity.customer.CustomerController;
import com.borrow.borrowsecurity.product.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class BorrowSecurityApplicationTests {
	// https://howtodoinjava.com/spring-boot/spring-boot-datasource-test-dev-prod-profiles/  <-- studies that
	// https://spring.io/guides/gs/testing-web/

	@Autowired
	private CustomerController customerController;

	@Autowired
	private ProductController productController;


	@Test
	void contextLoads() {
		assertThat(customerController).isNotNull();
		assertThat(productController).isNotNull();
	}

}
