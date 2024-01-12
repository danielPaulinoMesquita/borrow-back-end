package com.borrow.borrowsecurity.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product saveProduct(ProductRequest productRequest) {
        //fixme this mapper
        Product product = productMapper.productRequestToProduct(productRequest);
        return productRepository.save(product);

    }
}
