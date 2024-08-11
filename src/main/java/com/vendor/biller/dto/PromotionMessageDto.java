package com.vendor.biller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionMessageDto implements Serializable{
    String topic;
    String context;

}
