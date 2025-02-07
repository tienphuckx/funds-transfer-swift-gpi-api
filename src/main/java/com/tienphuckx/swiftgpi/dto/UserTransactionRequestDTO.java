package com.tienphuckx.swiftgpi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserTransactionRequestDTO {
    private String senderAccount;
    private String receiverAccount;
    private String receiverBankBIC;
    private String currency;
    private BigDecimal amount;
    private String reference;
}

