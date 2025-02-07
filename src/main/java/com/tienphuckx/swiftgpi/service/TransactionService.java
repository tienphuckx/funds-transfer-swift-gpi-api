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
        UserTransactionRequestFull fullRequest = new UserTransactionRequestFull();
        fullRequest.setSenderAccount(dto.getSenderAccount());
        fullRequest.setReceiverAccount(dto.getReceiverAccount());
        fullRequest.setReceiverBankBIC(dto.getReceiverBankBIC());
        fullRequest.setSenderBankBIC("BANKXYZ123"); // Lấy từ config hoặc DB
        fullRequest.setCurrency(dto.getCurrency());
        fullRequest.setAmount(dto.getAmount());
        fullRequest.setReference(dto.getReference());
        fullRequest.setMessageId(UUID.randomUUID().toString());
        fullRequest.setUetr(UUID.randomUUID().toString());
        fullRequest.setTransactionDate(LocalDateTime.now());

        log.info("Transaction enriched: {}", fullRequest);
        return fullRequest;
    }
}

