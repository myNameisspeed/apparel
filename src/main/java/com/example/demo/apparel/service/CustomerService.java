package com.example.demo.apparel.service;


import com.example.demo.apparel.dto.CreateCustomerDTO;
import com.example.demo.apparel.dto.ResponseDTO;

public interface CustomerService {
    ResponseDTO createCustomer(CreateCustomerDTO customer);
}
