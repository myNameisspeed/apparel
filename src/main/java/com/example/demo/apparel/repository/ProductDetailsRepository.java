package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetail,Integer> {

    Optional<ProductDetail> findAllByCategoryAndSubcategoryMappingIdAndIsDeletedAndIsActive(List<Integer> IdList,int isDeleted, int isActive);
}
