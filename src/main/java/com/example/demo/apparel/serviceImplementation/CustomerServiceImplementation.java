package com.example.demo.apparel.serviceImplementation;


import com.example.demo.apparel.dto.CreateCustomerDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.CustomerDetails;
import com.example.demo.apparel.repository.CustomerDetailRepository;
import com.example.demo.apparel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerDetailRepository customerDetailRepository;


    @Override
    public ResponseEntity<ResponseDTO> createCustomer(CreateCustomerDTO customer) {

        CustomerDetails customerDetails = new CustomerDetails();

        //check data already exist or not
        Optional<CustomerDetails> customerExist = customerDetailRepository.findByUniqueKeyAndIsDeleted(customer.getUniqueKey(), 0);

        customerDetails.setName(customer.getName());
        customerDetails.setMobileNumber(customer.getMobileNumber());

        if (customerExist.isEmpty()) {
            customerDetails.setUniqueKey(UUID.randomUUID().toString());
            customerDetails.setCreatedOn(LocalDateTime.now());
            customerDetails.setCreatedBy(1);
        } else {
            customerDetails.setId(customerExist.get().getId());
            customerDetails.setUniqueKey(customerExist.get().getUniqueKey());
            customerDetails.setUpdatedOn(LocalDateTime.now());
            customerDetails.setUpdatedBy(1);
            customerDetails.setCreatedBy(customerExist.get().getCreatedBy());
            customerDetails.setCreatedOn(customerExist.get().getCreatedOn());
        }
        customerDetailRepository.save(customerDetails);
        return ResponseEntity.ok().body(new ResponseDTO());
    }
}
