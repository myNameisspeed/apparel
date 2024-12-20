package com.example.demo.apparel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInputDTO {
    private String categoryId;
    private String subCategoryId;
    private String productName;
    private Float mrp;
    private Integer minimumQuantity;
    private Integer isMultipleRecords;
    private Integer sizeRefId;
    private Integer colorRefId;
}
