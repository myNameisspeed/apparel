package com.example.demo.apparel.controller;


import com.example.demo.apparel.dto.CreateCategoryDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody CreateCategoryDTO createCategory){
        ResponseEntity<ResponseDTO> createCategoryDetails = categoryService.createCategoryDetails(createCategory);
        return  createCategoryDetails;
    }
}
