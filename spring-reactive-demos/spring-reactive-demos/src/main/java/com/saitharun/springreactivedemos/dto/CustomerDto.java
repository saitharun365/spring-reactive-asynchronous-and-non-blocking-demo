package com.saitharun.springreactivedemos.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/****
 * This a Dto Class
 * @author : Sai Tharun Pathakota
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Integer customerId;
    private  String customerData;
}
