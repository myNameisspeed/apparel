package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {
}