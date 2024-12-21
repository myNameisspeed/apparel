package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Category;
import com.example.demo.apparel.entity.CategoryAndSubcategoryMappping;
import com.example.demo.apparel.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryAndSubcategoryMappingRepository extends JpaRepository<CategoryAndSubcategoryMappping,Integer> {
   Optional<CategoryAndSubcategoryMappping> findByCategoryAndSubcategoryAndIsDeleted(Category category, Subcategory SubCategoryId, int isDeleted);
}
