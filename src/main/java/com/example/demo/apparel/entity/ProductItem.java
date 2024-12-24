package com.example.demo.apparel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productitem" , uniqueConstraints = @UniqueConstraint(columnNames = {"id","uniquekey"}))
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uniqueKey", length = 40, nullable = false)
    private String uniqueKey;

    @ManyToOne
    @JoinColumn(name = "categoryAndSubCategoryMappingId")
    private ProductDetail productDetail;

    @Column(name ="colorRefId")
    private Integer colorRefId =null;

    @Column(name ="sizeRefId")
    private Integer sizeRefId =null;

    @Column(name ="isStickered")
    private Integer isStickered =null;

    @Column(name ="statusRefId")
    private Integer statusRefId =null;

    @Column(name ="productId" ,nullable = false)
    private String productId ;

    @Column(name ="topPicks")
    private Integer topPicks =0;

    @Column(name ="hotNew")
    private Integer hotNew =0;


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
}
