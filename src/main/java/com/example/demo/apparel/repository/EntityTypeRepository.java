package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityTypeRepository extends JpaRepository<EntityType,Integer> {
}
