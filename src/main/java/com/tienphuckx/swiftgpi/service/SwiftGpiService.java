package com.tienphuckx.swiftgpi.service;

import com.tienphuckx.swiftgpi.dto.UserTransactionRequestFull;
import com.tienphuckx.swiftgpi.swift.Pacs008Message;
import com.tienphuckx.swiftgpi.swift.Pacs008MessageFull;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class SwiftGpiService {

    private final FileService fileService;

    private final StringWriter xmlWriter = new StringWriter();

    public SwiftGpiService(FileService fileService) {
        this.fileService = fileService;
    }

    public String buildPacs008MessageFull(UserTransactionRequestFull transaction) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Pacs008MessageFull.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // UserTransactionRequestFull -> Pacs008MessageFull
            Pacs008MessageFull pacs008 = convertToPacs008(transaction);

            marshaller.marshal(pacs008, xmlWriter);

            // init file by timestamp + transactionId
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = "pacs008_" + transaction.getTransactionId() + "_" + timestamp + ".xml";

            fileService.saveToFile(fileName, xmlWriter.toString());

            return xmlWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("Error building pacs.008 XML", e);
        }
    }

    private Pacs008MessageFull convertToPacs008(UserTransactionRequestFull transaction) {
        Pacs008MessageFull pacs008 = new Pacs008MessageFull();

        pacs008.setMessageId(transaction.getMessageId());
        pacs008.setCreationDateTime(LocalDateTime.now());
        pacs008.setNumberOfTransactions(1);
        pacs008.setControlSum(transaction.getAmount());
        pacs008.setInstructionId(UUID.randomUUID().toString()); // Fake ID giao dịch
        pacs008.setEndToEndId("NOTPROVIDED");
        pacs008.setTransactionId(transaction.getTransactionId());
        pacs008.setChargeBearer("SHAR"); // Giả định mặc định "SHAR"

        // Init Interbank Settlement Amount
        Pacs008MessageFull.Amount amount = new Pacs008MessageFull.Amount();
        amount.setCurrency(transaction.getCurrency());
        amount.setValue(transaction.getAmount());
        pacs008.setInterbankSettlementAmount(amount);

        // Init Debtor info - sender
        Pacs008MessageFull.Party debtor = new Pacs008MessageFull.Party();
        debtor.setName("Fake Sender Name");

        Pacs008MessageFull.Address debtorAddress = new Pacs008MessageFull.Address();
        debtorAddress.setAddressLine("Fake Address Sender");
        debtor.setPostalAddress(debtorAddress);
        pacs008.setDebtor(debtor);

        // Init Debtor Account
        Pacs008MessageFull.OtherId debtorOtherId = new Pacs008MessageFull.OtherId();
        debtorOtherId.setId(transaction.getSenderAccount());

        Pacs008MessageFull.AccountId debtorAccountId = new Pacs008MessageFull.AccountId();
        debtorAccountId.setOtherId(debtorOtherId);

        Pacs008MessageFull.Account debtorAccount = new Pacs008MessageFull.Account();
        debtorAccount.setId(debtorAccountId);
        pacs008.setDebtorAccount(debtorAccount);

        // Init Debtor Agent (Bank of sender)
        Pacs008MessageFull.FinancialInstitution debtorAgent = new Pacs008MessageFull.FinancialInstitution();
        debtorAgent.setBic(transaction.getSenderBankBIC());
        pacs008.setDebtorAgent(debtorAgent);

        // Init Creditor Agent (Bank of receiver)
        Pacs008MessageFull.FinancialInstitution creditorAgent = new Pacs008MessageFull.FinancialInstitution();
        creditorAgent.setBic(transaction.getReceiverBankBIC());
        pacs008.setCreditorAgent(creditorAgent);

        // Init Creditor info (Receiver)
        Pacs008MessageFull.Party creditor = new Pacs008MessageFull.Party();
        creditor.setName("Fake Receiver Name");

        Pacs008MessageFull.Address creditorAddress = new Pacs008MessageFull.Address();
        creditorAddress.setAddressLine("Fake Address Receiver");
        creditor.setPostalAddress(creditorAddress);
        pacs008.setCreditor(creditor);

        // Init Creditor Account
        Pacs008MessageFull.OtherId creditorOtherId = new Pacs008MessageFull.OtherId();
        creditorOtherId.setId(transaction.getReceiverAccount());

        Pacs008MessageFull.AccountId creditorAccountId = new Pacs008MessageFull.AccountId();
        creditorAccountId.setOtherId(creditorOtherId);

        Pacs008MessageFull.Account creditorAccount = new Pacs008MessageFull.Account();
        creditorAccount.setId(creditorAccountId);
        pacs008.setCreditorAccount(creditorAccount);

        // Init Remittance Info
        Pacs008MessageFull.RemittanceInfo remittanceInfo = new Pacs008MessageFull.RemittanceInfo();
        remittanceInfo.setUnstructured(transaction.getReference());
        pacs008.setRemittanceInfo(remittanceInfo);

        return pacs008;
    }

}
