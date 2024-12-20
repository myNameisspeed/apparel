package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findByUniqueKeyAndIsDeleted(String uniqueKey,int isDeleted);

}
