package com.example.demo.apparel.serviceImplementation;

import com.example.demo.apparel.AppConstants.AppConstant;
import com.example.demo.apparel.dto.CreateSubCategoryDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.Category;
import com.example.demo.apparel.entity.Subcategory;
import com.example.demo.apparel.repository.CategoryRepository;
import com.example.demo.apparel.repository.SubcategoryRepository;
import com.example.demo.apparel.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


public class SubCategoryServiceImplementation implements SubCategoryService {

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    SubcategoryRepository subcategoryRepo;

    @Override
    public ResponseEntity<ResponseDTO> createOrUpdateSubCategoryService(CreateSubCategoryDTO CreateSubCategoryDTO){
        try{
            Optional<Category> categoryData = categoryRepo.findByNameAndIsDeleted(CreateSubCategoryDTO.getCategoryId(), AppConstant.ZERO);
            System.out.print("categoryData"+categoryData);
            Optional<Subcategory> subCategoryData =  subcategoryRepo.findByUniqueKey(CreateSubCategoryDTO.getSubCategoryId(),AppConstant.ZERO);
            System.out.print("subCategoryData"+subCategoryData);

            Optional<Subcategory> findExistingSubCategoryName = subcategoryRepo.findByName(CreateSubCategoryDTO.getSubCategoryName(),AppConstant.ZERO);
            if(subCategoryData.isPresent()){
//                Optional<Subcategory>
            }
            if(CreateSubCategoryDTO.getSubCategoryId().equals("") && findExistingSubCategoryName.isPresent()){

                return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.IM_USED,AppConstant.NAME_ALREADY_EXIST));
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
