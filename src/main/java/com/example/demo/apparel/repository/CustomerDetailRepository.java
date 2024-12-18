package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailRepository extends JpaRepository<CustomerDetails,Integer> {

}
