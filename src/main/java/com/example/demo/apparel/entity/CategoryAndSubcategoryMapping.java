package com.example.demo.apparel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoryandsubcategorymapping" , uniqueConstraints = @UniqueConstraint(columnNames = {"id","uniqueKey"}))
public class CategoryAndSubcategoryMapping {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "uniqueKey", length = 40, nullable = false)
    private String uniqueKey;

    @ManyToOne
    @JoinColumn(name = "categoryid" , nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategoryid" , nullable = false)
    private Subcategory subcategory;

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

    @OneToMany(mappedBy = "categoryAndSubcategoryMapping", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductDetail> productDetails;

}
