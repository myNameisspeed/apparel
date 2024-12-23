package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Category;
import com.example.demo.apparel.entity.CategoryAndSubcategoryMapping;
import com.example.demo.apparel.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryAndSubcategoryMappingRepository extends JpaRepository<CategoryAndSubcategoryMapping,Integer> {

   Optional<CategoryAndSubcategoryMapping> findByCategoryAndSubcategoryAndIsDeleted(Category category, Subcategory SubCategoryId, int isDeleted);
   Optional<CategoryAndSubcategoryMapping> findByCategoryAndIsDeletedAndIsActive(Category category, int isDeleted,int isActive);
   @Modifying
   @Query("UPDATE CategoryAndSubcategoryMapping c SET c.isDeleted=1 , c.isActive = 0 WHERE c.isDeleted=0 and c.categoryid= : categoryId ")
   int UpdateCategoryAndSubcategoryMappingByCategoryId(@Param("categoryId") Integer id);


}
