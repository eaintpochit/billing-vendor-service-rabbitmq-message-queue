package com.vendor.biller.controller;

import com.vendor.biller.dto.BillerRequestDto;
import com.vendor.biller.service.BillerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
public class BillerRequestController {

    @Autowired
    private BillerRequestService billerRequestService;

    @PostMapping("/request")
    public String sendBillerRequest(@RequestBody BillerRequestDto billerRequest) {
        return billerRequestService.send(billerRequest);
    }
}
