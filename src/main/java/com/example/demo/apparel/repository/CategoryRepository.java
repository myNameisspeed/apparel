package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findByUniqueKeyAndIsDeleted(String uniqueKey,int isDeleted);
    Optional<Category> findByNameAndIsDeleted(String name,int isDeleted);

    @Modifying
    @Query("UPDATE Category c SET c.isDeleted = 1, c.isActive = 0 WHERE c.isDeleted = 0 AND c.id = :categoryId")
    int UpdateByCategoryByCategoryId(@Param("categoryId") Integer id);


}
