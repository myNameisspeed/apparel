package com.example.demo.apparel.serviceImplementation;

import com.example.demo.apparel.dto.CreateCategoryDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.Category;
import com.example.demo.apparel.repository.CategoryRepository;
import com.example.demo.apparel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class createCategoryDetailsImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ResponseDTO> createCategoryDetails(CreateCategoryDTO createCategory) {

       Optional<Category> categoryOptional= categoryRepository.findByUniqueKeyAndIsDeleted(createCategory.getUniqueKey(),0);
       Category category = new Category();

       if(categoryOptional.isEmpty()){
           category.setUniqueKey(UUID.randomUUID().toString());
           category.setCreatedOn(LocalDateTime.now());
           category.setCreatedBy(1);
       }
       else {
           category.setId(categoryOptional.get().getId());
           category.setUpdatedBy(1);
           category.setUpdatedOn(LocalDateTime.now());
       }
       categoryRepository.save(category);
       return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK,"Category Created Successfully"));
    }
}
