package com.example.demo.apparel.serviceImplementation;

import com.example.demo.apparel.dto.ProductInputDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.Category;
import com.example.demo.apparel.entity.CategoryAndSubcategoryMappping;
import com.example.demo.apparel.entity.Subcategory;
import com.example.demo.apparel.repository.CategoryAndSubcategoryMapppingRepository;
import com.example.demo.apparel.repository.CategoryRepository;
import com.example.demo.apparel.repository.SubcategoryRepository;
import com.example.demo.apparel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    private CategoryAndSubcategoryMapppingRepository categoryAndSubcategoryMapppingRepository;

    @Override
    public ResponseEntity<ResponseDTO> createProductDetails(ProductInputDTO product) {

        CategoryAndSubcategoryMappping categoryAndSubcategoryMappping =createCategoryAndSubcategoryMappping
                (product.getCategoryId(),product.getSubCategoryId());
        return null;
    }

    public CategoryAndSubcategoryMappping createCategoryAndSubcategoryMappping(String categoryId, String subCategoryID){


        Optional<CategoryAndSubcategoryMappping> categoryAndSubcategoryMappping;
       Optional<Category> category= categoryRepository.findByUniqueKeyAndIsDeleted(categoryId,0);
       Optional<Subcategory> subcategory=subcategoryRepository.findByUniqueKeyAndIsDeleted(subCategoryID,0);

       if(category.isPresent() && subcategory.isPresent()){
          categoryAndSubcategoryMappping=categoryAndSubcategoryMapppingRepository
                                 .findByCategoryidAndSubcategoryidAndIsDeleted(categoryId,subCategoryID,0);
       }
       else {
           CategoryAndSubcategoryMappping categoryAndSubcategoryMapppingObj=new CategoryAndSubcategoryMappping();

           categoryAndSubcategoryMapppingObj.setUniqueKey(UUID.randomUUID().toString());
//           categoryAndSubcategoryMapppingObj.setCategory();
       }
        return null;
    }

}
