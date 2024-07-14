package com.vendor.biller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    String body;
    String subject;
    String from;
    String header;
}
