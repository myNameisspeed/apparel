package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.ReferenceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceGroupRepository extends JpaRepository<ReferenceGroup,Integer> {
}
