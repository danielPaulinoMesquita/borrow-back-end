package com.borrow.borrowsecurity.customer;

import com.borrow.borrowsecurity.product.Product;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String document;
    private String description;

    @OneToMany(mappedBy = "customer")
    private List<Product> products;
}