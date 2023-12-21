package com.borrow.borrowsecurity.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}
