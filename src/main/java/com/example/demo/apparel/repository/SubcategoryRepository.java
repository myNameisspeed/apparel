package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Integer> {

    Optional<Subcategory> findByUniqueKeyAndIsDeleted(String uniqueKey,int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE Subcategory c SET c.isDeleted=1 , c.isActive = 0 WHERE c.isDeleted=0 and c.category.id = :categoryId ")
    int UpdateSubCategoryByCategoryId(@Param("categoryId") Integer categoryId);

}
