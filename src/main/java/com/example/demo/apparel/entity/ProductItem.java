package com.example.demo.apparel.entity;


import jakarta.persistence.*;

@Table(name = "ProductItem", uniqueConstraints = @UniqueConstraint(columnNames = {"id","uniqueKey"}))
public class ProductItem {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "uniqueKey", length = 40, nullable = false)
    private String uniqueKey;

    @ManyToOne
    @JoinColumn(name = "productDetailId", nullable = false)
    private ProductDetails productDetails;


}
