package com.example.demo.apparel.service;


import com.example.demo.apparel.dto.CreateCustomerDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<ResponseDTO> createCustomer(CreateCustomerDTO customer);
}
