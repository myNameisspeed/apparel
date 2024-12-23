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
@Table(name = "tbl_vendor" , uniqueConstraints = @UniqueConstraint(columnNames = {"id","uniquekey"}))
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uniquekey", length = 40, nullable = false)
    private String uniqueKey;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String emailId=null;

    @Column(name = "mobileNumber")
    private String mobileNumber =null;

    @Column(name ="websiteUrl")
    private String websiteUrl ;

    @Column(name ="contactName")
    private String contactName =null;

    @Column(name ="street_area")
    private String street_area =null;

    @Column(name ="city")
    private String city =null;

    @Column(name ="zipcode")
    private String zipcode =null;

    @Column(name ="stateRefId")
    private Integer stateRefId =null ;

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

    @OneToMany(mappedBy ="vendor",cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<ProductDetail> productDetails;
}
