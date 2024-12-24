package com.example.demo.apparel.controller;


import com.example.demo.apparel.dto.CreateCategoryDTO;
import com.example.demo.apparel.dto.CreateCategoryListDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody CreateCategoryDTO createCategory){
        return categoryService.createCategoryDetails(createCategory);
    }

    @PostMapping("/createCategoryList")
    public ResponseEntity<ResponseDTO> createCategoryList(@RequestBody CreateCategoryListDTO createCategoryList){
        return categoryService.createCategoryListDetails(createCategoryList);
    }

    @GetMapping("/getCategory")
    public ResponseEntity<ResponseDTO> getCategoryController(){
        return categoryService.getCategoryService();
    }

}
