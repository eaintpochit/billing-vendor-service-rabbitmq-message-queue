package com.vendor.biller.controller;

import com.vendor.biller.dto.BillerRequestDto;
import com.vendor.biller.service.IBillerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
public class BillerRequestController {

    @Autowired
    private IBillerRequestService billedRequestService;

    @PostMapping("/request")
    public ResponseEntity<?> sendBillerRequest(@RequestBody BillerRequestDto billerRequest) {
        return billedRequestService.sendBillRequest(billerRequest);
    }
}
