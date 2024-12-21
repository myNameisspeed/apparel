package com.example.demo.apparel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryListDTO {
    private String categoryId;
    private String categoryName;
    private String description;
    private int isActive;
    private int isDeleted;
    private List<AttachmentDTO> attachment;
}
