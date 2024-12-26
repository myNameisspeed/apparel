package com.example.demo.apparel.service;

import com.example.demo.apparel.dto.CreateSubCategoryDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface SubCategoryService {
    public ResponseEntity<ResponseDTO> createOrUpdateSubCategoryService(CreateSubCategoryDTO CreateSubCategoryDTO);

}
