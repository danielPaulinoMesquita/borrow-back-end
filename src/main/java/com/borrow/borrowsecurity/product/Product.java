package com.borrow.borrowsecurity.product;

import com.borrow.borrowsecurity.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String days;
    private String value;
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
