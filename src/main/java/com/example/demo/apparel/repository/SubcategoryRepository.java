package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Integer> {
}
