package com.borrow.borrowsecurity.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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

    public Product saveProduct(ProductRequest productRequest, MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();

        try {
            assert fileName != null;
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence " + fileName);
            }
            if (file.getBytes().length > (1024 * 1024)) {
                throw new Exception("File size exceeds maximum limit");
            }

            Product product = productMapper.productRequestToProduct(productRequest);
            product.setImageData(Base64.getEncoder().encodeToString(file.getBytes()));

            return productRepository.save(product);

        } catch (Exception e) {
            throw new Exception("Could not save File:"+fileName);
        }

    }
}
