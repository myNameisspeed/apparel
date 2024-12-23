package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetail, Integer> {

    @Query("SELECT p FROM ProductDetail p WHERE p.categoryAndSubcategoryMapping.id IN :idList AND p.isDeleted = :isDeleted AND p.isActive = :isActive")
    Optional<List<ProductDetail>> findAllByCategoryAndSubcategoryMappingIdInAndIsDeletedAndIsActive(
            @Param("idList") List<Integer> idList,
            @Param("isDeleted") int isDeleted,
            @Param("isActive") int isActive);
}
