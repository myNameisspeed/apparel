package com.example.demo.apparel.service;


import com.example.demo.apparel.dto.ProductInputDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.CategoryAndSubcategoryMapping;
import org.springframework.http.ResponseEntity;


public interface ProductService {
    ResponseEntity<ResponseDTO> createProductDetails(ProductInputDTO product);
    CategoryAndSubcategoryMapping createCategoryAndSubcategoryMapping(String categoryId, String subCategoryID);
}
