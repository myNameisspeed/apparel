package com.example.demo.apparel.dto;

import lombok.*;


import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCustomerDTO {
    private Integer id;
    private String name;
    private String mobileNumber;

//    public  String getName(){
//        return  this.name;
//    }


}
