package com.example.demo.apparel.service;

import com.example.demo.apparel.dto.CreateCategoryDTO;
import com.example.demo.apparel.dto.CreateCategoryListDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    public ResponseEntity<ResponseDTO> createCategoryDetails(CreateCategoryDTO createCategory) ;
    public ResponseEntity<ResponseDTO> createCategoryListDetails(CreateCategoryListDTO createCategoryList);

    public ResponseEntity<ResponseDTO> getCategoryService();
}
