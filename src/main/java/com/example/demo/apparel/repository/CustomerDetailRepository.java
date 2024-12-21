package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetails,Integer> {

    Optional<CustomerDetails> findByUniqueKeyAndIsDeleted(String uniqueKey, int isDeleted);
}
