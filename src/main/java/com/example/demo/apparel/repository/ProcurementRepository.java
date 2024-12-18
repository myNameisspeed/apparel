package com.example.demo.apparel.repository;

import com.example.demo.apparel.entity.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcurementRepository extends JpaRepository<Procurement,Integer> {
}
