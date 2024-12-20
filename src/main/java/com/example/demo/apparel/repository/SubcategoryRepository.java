package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Integer> {

    Optional<Subcategory> findByUniqueKeyAndIsDeleted(String uniqueKey,int isDeleted);
}
