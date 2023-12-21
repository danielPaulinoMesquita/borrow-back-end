package com.borrow.borrowsecurity.product;

import com.borrow.borrowsecurity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductByCustomer(Customer customer);

}
