package com.tienphuckx.swiftgpi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserTransactionRequestFull {
    private String senderAccount;
    private String receiverAccount;
    private String receiverBankBIC;
    private String senderBankBIC;
    private String currency;
    private BigDecimal amount;
    private String reference;
    private String messageId;
    private String uetr; // Unique End-to-End Transaction Reference
    private LocalDateTime transactionDate;
}
