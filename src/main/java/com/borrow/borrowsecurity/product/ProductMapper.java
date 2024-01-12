package com.borrow.borrowsecurity.product;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;
    public ProductMapper(){
        modelMapper = new ModelMapper();
    }

    public Product productRequestToProduct(ProductRequest productRequest){
        return modelMapper.map(productRequest, Product.class);
    }

    public ProductRequest productToProductRequest(Product product){
        return modelMapper.map(product, ProductRequest.class);
    }
}
