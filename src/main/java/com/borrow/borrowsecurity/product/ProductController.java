package com.borrow.borrowsecurity.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Product> getProduct(@RequestParam Integer id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }


}
