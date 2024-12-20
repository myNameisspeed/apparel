package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.CoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreCategoryRepository extends JpaRepository<CoreCategory,Integer> {
}
