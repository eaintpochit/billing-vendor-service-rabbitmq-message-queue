package com.vendor.biller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto implements Serializable {
    String body;
    String subject;
    String from;
    String header;
    String receiverMail;
    String replyTo;
}
