package com.tienphuckx.swiftgpi.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserTransactionRequestFull {
    @Builder.Default
    private String transactionId = UUID.randomUUID().toString();
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
