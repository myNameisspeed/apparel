package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.CategoryAndSubcategoryMappping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryAndSubcategoryMapppingRepository extends JpaRepository<CategoryAndSubcategoryMappping,Integer> {
    Optional<CategoryAndSubcategoryMappping> findByCategoryidAndSubcategoryidAndIsDeleted(String categoryId,String subCategoryId,int isDeleted);
}
