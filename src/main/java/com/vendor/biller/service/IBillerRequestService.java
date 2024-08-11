package com.vendor.biller.service;

import com.vendor.biller.dto.BillerRequestDto;
import org.springframework.http.ResponseEntity;

public interface IBillerRequestService {

     ResponseEntity<?> sendBillRequest(BillerRequestDto dto);
}
