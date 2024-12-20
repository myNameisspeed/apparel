package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findByUniqueKeyAndIsDeleted(String uniqueKey,int isDeleted);

}
