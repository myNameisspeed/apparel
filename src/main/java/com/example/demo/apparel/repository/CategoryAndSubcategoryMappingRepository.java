package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.CategoryAndSubcategoryMappping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryAndSubcategoryMappingRepository extends JpaRepository<CategoryAndSubcategoryMappping,Integer> {
   Optional<CategoryAndSubcategoryMappping> findByCategoryidAndSubcategoryidAndIsDeleted(String categoryId,String SubCategoryId,int isDeleted);
}
