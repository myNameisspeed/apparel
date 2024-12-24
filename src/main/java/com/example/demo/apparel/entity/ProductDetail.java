package com.example.demo.apparel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productdetail", uniqueConstraints = @UniqueConstraint(columnNames = {"id","uniqueKey"}))

public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uniqueKey", length = 40, nullable = false)
    private String uniqueKey;

    @ManyToOne
    @JoinColumn(name = "categoryAndSubCategoryMappingId")
    private CategoryAndSubcategoryMapping categoryAndSubcategoryMapping=null;

    @ManyToOne
    @JoinColumn(name = "coreCategoryId")
    private CoreCategory coreCategory=null;

    @ManyToOne
    @JoinColumn(name = "vendorId")
    private Vendor vendor=null;

    @Column(name ="isMultipleRecords")
    private Integer isMultipleRecords=null;

    @Column(name ="productName",nullable = false)
    private String productName ;

    @Column(name ="mrp")
    private Double mrp =0.00;

    @Column(name ="minimumQuantity")
    private Integer minimumQuantity =0;

    @Column(name ="onlineDiscount")
    private Double onlineDiscount =0.00;

    @Column(name = "salesType")
    private  Integer salesType =1;

    @Column(name = "description")
    private  String description ;

    @Column(name = "purchaseDate")
    private  LocalDateTime purchaseDate ;

    @Column(name ="isActive")
    private int isActive =1;

    @Column(name ="createdBy",nullable = false)
    private Integer createdBy ;

    @Column(name ="createdOn" ,nullable = false)
    private LocalDateTime createdOn ;

    @Column(name ="updatedBy")
    private Integer updatedBy =null ;

    @Column(name ="updatedOn")
    private LocalDateTime updatedOn =null;

    @Column(name ="isDeleted")
    private int isDeleted =0;

    @OneToMany(mappedBy ="productDetail",cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<ProductItem> productItems;
}
