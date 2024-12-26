package com.example.demo.apparel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateSubCategoryDTO {
    private String categoryId;
    private String subCategoryId;
    private String subCategoryName;
    private String description;
    private Integer displayRank;
    private Integer isUpdated;
    private Integer isActive;
    private Integer isDeleted;
    private List<AttachmentDTO> attachment;
}
