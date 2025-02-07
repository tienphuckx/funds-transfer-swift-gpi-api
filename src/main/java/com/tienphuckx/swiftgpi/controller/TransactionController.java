package com.tienphuckx.swiftgpi.controller;

import com.tienphuckx.swiftgpi.dto.UserTransactionRequestDTO;
import com.tienphuckx.swiftgpi.dto.UserTransactionRequestFull;
import com.tienphuckx.swiftgpi.service.SwiftGpiService;
import com.tienphuckx.swiftgpi.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Log4j2
public class TransactionController {

    private final TransactionService transactionService;
    private final SwiftGpiService swiftGpiService;

    public TransactionController(TransactionService transactionService, SwiftGpiService swiftGpiService) {
        this.transactionService = transactionService;
        this.swiftGpiService = swiftGpiService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> processTransaction(@RequestBody UserTransactionRequestDTO requestDTO) {
        UserTransactionRequestFull fullRequest = transactionService.enrichTransaction(requestDTO);
        String pacs008Xml = swiftGpiService.buildPacs008(fullRequest);

        log.info("Generated pacs.008: {}", pacs008Xml);

        return ResponseEntity.ok("Transaction Processed Successfully");
    }
}

