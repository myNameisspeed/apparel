package com.example.demo.apparel.dto;

import lombok.*;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO {
    private String name;
    private String mobileNumber;
    private String uniqueKey;
}
