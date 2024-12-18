package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoy  extends JpaRepository<Category,Integer> {

}
