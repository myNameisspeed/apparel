package com.example.demo.apparel.serviceImplementation;


import com.example.demo.apparel.dto.CreateCustomerDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.repository.CustomerDetailRepository;
import com.example.demo.apparel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    @Override
    public ResponseDTO createCustomer(CreateCustomerDTO customer) {

        System.out.println("customer "+ customer.toString());

        return null;
    }
}
