package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Integer> {
        Optional<ProductItem> findByUniqueKeyAndIsDeleted(String uniqueKey,int isDeleted);
}
