package com.example.demo.apparel.controller;

import com.example.demo.apparel.dto.CreateSubCategoryDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subCategory")
public class SubcCategoryController {
    @Autowired


    @PostMapping("/createOrUpdateSubCategory")
    public ResponseEntity<ResponseDTO> createOrUpdateSubCategory(@RequestBody CreateSubCategoryDTO createOrUpdateSubCategory){
        return

    }
}
