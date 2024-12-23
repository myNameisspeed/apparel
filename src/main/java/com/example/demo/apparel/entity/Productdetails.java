package com.example.demo.apparel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Productdetails", uniqueConstraints = @UniqueConstraint(columnNames = {"id","uniqueKey"}))
public class Productdetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="uniqueKey",length = 40, nullable = false)
    private String uniqueKey;

    @ManyToOne()
    @JoinColumn(name = "categoryAndSubCategoryMappingId", nullable = false)
    private CategoryAndSubcategoryMappping categoryAndSubCategoryMapping;

    @ManyToOne()
    @JoinColumn(name="vendorId", nullable = true)
    private Vendor vendor;

    @Column(name = "isMultipleRecords", nullable = false)
    private String isMultipleRecords;

    @Column(name = "productName", nullable = true)
    private String productName;

    @Column(name="mrp", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00",nullable = false)
    private BigDecimal mrp = new BigDecimal("0.00");

    @Column(name = "minimumQuantity", columnDefinition = "BIGINT DEFAULT 0")
    private Long minimumQuantity;

    @Column(name="onlineDiscount", columnDefinition= "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal onlineDiscount = new BigDecimal("0.00");

    @Column(name="salesType", columnDefinition = "int DEFAULT 1")
    private Integer salesType;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime purchaseDate;

    @Column(name="isActive", columnDefinition = "int default 1")
    private Integer isActive;

    @Column(name="createdBy", nullable = false)
    private Integer createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdOn;

    @Column(name="updatedBy", nullable = true)
    private Integer updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedOn;

    @Column(name = "isDeleted", columnDefinition = "int not null default 0")
    private Integer isDeleted;

}
