package com.example.demo.apparel.controller;


import com.example.demo.apparel.dto.CreateCustomerDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.CustomerDetails;
import com.example.demo.apparel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createUser")
    public ResponseEntity<ResponseDTO> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO){
        System.out.println("createCustomerDTO "+createCustomerDTO);
        ResponseEntity<ResponseDTO> createCustomer=customerService.createCustomer(createCustomerDTO);
       return createCustomer;
    }
}
