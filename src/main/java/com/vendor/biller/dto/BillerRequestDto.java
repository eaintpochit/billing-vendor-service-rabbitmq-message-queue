package com.vendor.biller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillerRequestDto implements Serializable {
    private String mobileNumber;
    private Double amount;

}
