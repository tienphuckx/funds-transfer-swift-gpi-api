package com.tienphuckx.swiftgpi.service;

import com.tienphuckx.swiftgpi.dto.UserTransactionRequestDTO;
import com.tienphuckx.swiftgpi.dto.UserTransactionRequestFull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Log4j2
public class TransactionService {

    public UserTransactionRequestFull enrichTransaction(UserTransactionRequestDTO dto) {
        UserTransactionRequestFull fullRequest = UserTransactionRequestFull.builder()
                .transactionId(UUID.randomUUID().toString()) // Auto-generate transaction ID
                .senderAccount(dto.getSenderAccount())
                .receiverAccount(dto.getReceiverAccount())
                .receiverBankBIC(dto.getReceiverBankBIC())
                .senderBankBIC("BANKXYZ123") // May get from config
                .currency(dto.getCurrency())
                .amount(dto.getAmount())
                .reference(dto.getReference())
                .messageId(UUID.randomUUID().toString()) // Generate message ID
                .uetr(UUID.randomUUID().toString()) // Generate UETR
                .transactionDate(LocalDateTime.now())
                .build();

        log.info("Transaction enriched: {}", fullRequest);
        return fullRequest;
    }

}

