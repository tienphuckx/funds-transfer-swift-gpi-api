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
                .senderBankBIC("BANKXYZ123") // Lấy từ config hoặc DB
                .currency(dto.getCurrency())
                .amount(dto.getAmount())
                .reference(dto.getReference())
                .messageId(UUID.randomUUID().toString()) // Sinh message ID
                .uetr(UUID.randomUUID().toString()) // Sinh UETR
                .transactionDate(LocalDateTime.now()) // Set thời gian hiện tại
                .build();

        log.info("Transaction enriched: {}", fullRequest);
        return fullRequest;
    }

}

