package com.example.demo.apparel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_attachment",uniqueConstraints = @UniqueConstraint(columnNames = {"id","uniqueKey"}))
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uniqueKey", length = 40, nullable = false)
    private String uniqueKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entityTypeId", nullable = false)
    private EntityType entityType;

    @Column(name = "entityId", nullable = false)
    private String entityId;

    @Column(name = "fileUuid", nullable = false)
    private String fileUuid;

    @Column(name = "mimeType", nullable = false)
    private String mimeType;

    @Column(name = "fileSize", nullable = false)
    private String fileSize;

    @Column(name = "coverImage", nullable = false)
    private Integer coverImage;

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
