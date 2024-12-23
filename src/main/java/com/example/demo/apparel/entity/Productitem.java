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
@Table(name = "Productitem", uniqueConstraints = @UniqueConstraint(columnNames = {"id","uniqueKey"}))
public class Productitem {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "uniqueKey", length = 40, nullable = false)
    private String uniqueKey;

    @ManyToOne()
    @JoinColumn(name = "productDetailId", nullable = false)
    private Productdetails productDetailId;

    @Column(name = "colorRefId", columnDefinition = "int default null")
    private Integer colorRefId;

    @Column(name = "sizeRefId",nullable = true)
    private Integer sizeRefId;

    @Column(name="isStickered",columnDefinition = "int default 0")
    private  Integer isStickered;

    @Column(name = "statusRefId", nullable = true)
    private Integer statusRefId;

    @Column(name = "productId", nullable = false)
    private String productId;

    @Column(name = "topPicks", nullable = true)
    private Integer topPicks;

    @Column(name = "hotNew", nullable = true)
    private Integer hotNew;

    @Column(name = "isActive", columnDefinition = "int default 1")
    private Integer isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdOn")
    private LocalDateTime createdOn;

    @Column(name="updatedBy", nullable = true)
    private Integer updatedby;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedOn")
    private LocalDateTime updatedOn;

    @Column(name = "isDeleted", columnDefinition = "int not null default 0")
    private Integer isDeleted;
}
