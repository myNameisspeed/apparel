package com.example.demo.apparel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_entitytype",uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))

public class EntityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

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

    @OneToMany(mappedBy ="entityType",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Attachment> attachments;

}
