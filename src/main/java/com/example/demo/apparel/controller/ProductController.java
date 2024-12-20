package com.example.demo.apparel.controller;


import com.example.demo.apparel.dto.ProductInputDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.Category;
import com.example.demo.apparel.entity.CategoryAndSubcategoryMappping;
import com.example.demo.apparel.service.CustomerService;
import com.example.demo.apparel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ResponseDTO> createProductDetails(ProductInputDTO productInput){
        ResponseEntity<ResponseDTO> createProduct= productService.createProductDetails(productInput);
        return createProduct;
    }

}
