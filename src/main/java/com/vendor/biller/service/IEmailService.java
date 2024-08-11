package com.vendor.biller.service;

import com.vendor.biller.dto.PromotionMessageDto;

import java.util.List;

public interface IEmailService {
    public void sendBulkEmail(String message);
}
