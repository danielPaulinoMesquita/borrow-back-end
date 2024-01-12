package com.borrow.borrowsecurity.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Product> getProduct(@RequestParam Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping(value = "/save-product")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.saveProduct(productRequest);
        return ResponseEntity.ok(product);
    }

}
