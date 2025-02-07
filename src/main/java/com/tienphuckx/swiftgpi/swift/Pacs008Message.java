package com.tienphuckx.swiftgpi.swift;

import com.tienphuckx.swiftgpi.dto.UserTransactionRequestFull;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

//@XmlRootElement(name = "Pacs008MessageDocument")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pacs008Message {

    @XmlElement(name = "FIToFICstmrCdtTrf")
    private CreditTransfer creditTransfer;

    public Pacs008Message(UserTransactionRequestFull transaction) {
        this.creditTransfer = new CreditTransfer(transaction);
    }

    public Pacs008Message() {} // Required for JAXB

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CreditTransfer {
        @XmlElement(name = "GrpHdr")
        private GroupHeader groupHeader;

        @XmlElement(name = "CdtTrfTxInf")
        private TransactionInformation transactionInformation;

        public CreditTransfer(UserTransactionRequestFull transaction) {
            this.groupHeader = new GroupHeader(transaction);
            this.transactionInformation = new TransactionInformation(transaction);
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GroupHeader {
        @XmlElement(name = "MsgId")
        private String messageId;

        @XmlElement(name = "CreDtTm")
        private String creationDateTime;

        public GroupHeader(UserTransactionRequestFull transaction) {
            this.messageId = transaction.getMessageId();
            this.creationDateTime = transaction.getTransactionDate().toString();
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TransactionInformation {
        @XmlElement(name = "PmtId")
        private PaymentId paymentId;

        @XmlElement(name = "IntrBkSttlmAmt")
        private Amount interbankSettlementAmount;

        @XmlElement(name = "Dbtr")
        private Party debtor;

        @XmlElement(name = "Cdtr")
        private Party creditor;

        public TransactionInformation(UserTransactionRequestFull transaction) {
            this.paymentId = new PaymentId(transaction);
            this.interbankSettlementAmount = new Amount(transaction.getCurrency(), transaction.getAmount());
            this.debtor = new Party(transaction.getSenderAccount());
            this.creditor = new Party(transaction.getReceiverAccount());
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PaymentId {
        @XmlElement(name = "InstrId")
        private String instructionId;

        @XmlElement(name = "UETR")
        private String uetr;

        public PaymentId(UserTransactionRequestFull transaction) {
            this.instructionId = transaction.getMessageId();
            this.uetr = transaction.getUetr();
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Amount {
        @XmlAttribute(name = "Ccy")
        private String currency;

        @XmlValue
        private BigDecimal amount;

        public Amount(String currency, BigDecimal amount) {
            this.currency = currency;
            this.amount = amount;
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Party {
        @XmlElement(name = "Nm")
        private String name;

        public Party(String name) {
            this.name = name;
        }
    }
}
