package com.tienphuckx.swiftgpi.swift;

import com.tienphuckx.swiftgpi.utils.BigDecimalAdapter;
import com.tienphuckx.swiftgpi.utils.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor // Thêm constructor không tham số để JAXB hoạt động
@XmlRootElement(name = "Document")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pacs008MessageFull {
    //, namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08"

    @XmlElement(name = "MsgId")
    private String messageId; // Mã tin nhắn duy nhất

    @XmlElement(name = "CreDtTm")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationDateTime;

    @XmlElement(name = "NbOfTxs")
    private int numberOfTransactions; // Số lượng giao dịch

    @XmlElement(name = "CtrlSum")
    private BigDecimal controlSum; // Tổng số tiền

    @XmlElement(name = "InstrId")
    private String instructionId; // Funds transfer code

    @XmlElement(name = "EndToEndId")
    private String endToEndId; // reference code

    @XmlElement(name = "TxId")
    private String transactionId; // ID

    @XmlElement(name = "IntrBkSttlmAmt")
    private Amount interbankSettlementAmount; // Amount + money type

    @XmlElement(name = "ChrgBr")
    private String chargeBearer; // Fee type(SHA, OUR, BEN)

    @XmlElement(name = "Dbtr")
    private Party debtor; // Người gửi

    @XmlElement(name = "DbtrAcct")
    private Account debtorAccount; // Bank account of sender

    @XmlElement(name = "DbtrAgt")
    private FinancialInstitution debtorAgent; // Bank of sender

    @XmlElement(name = "CdtrAgt")
    private FinancialInstitution creditorAgent; // Bank of receiver

    @XmlElement(name = "Cdtr")
    private Party creditor; // Receiver

    @XmlElement(name = "CdtrAcct")
    private Account creditorAccount; // Receiver bank account

    @XmlElement(name = "RmtInf")
    private RemittanceInfo remittanceInfo; // content of money transfer

    // List of subclass freshet for complicate data
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Amount {
        @XmlAttribute(name = "Ccy")
        private String currency;

        @XmlElement(name = "Value")
        @XmlJavaTypeAdapter(BigDecimalAdapter.class)
        private BigDecimal value;

    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Party {
        @XmlElement(name = "Nm")
        private String name; // Name of sender

        @XmlElement(name = "PstlAdr")
        private Address postalAddress; // Address
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Address {
        @XmlElement(name = "AdrLine")
        private String addressLine; // Full address
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Account {
        @XmlElement(name = "Id")
        private AccountId id;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AccountId {
        @XmlElement(name = "Othr")
        private OtherId otherId;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class OtherId {
        @XmlElement(name = "Id")
        private String id; // Bank account
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class FinancialInstitution {
        @XmlElement(name = "BICFI")
        private String bic;
    }


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RemittanceInfo {
        @XmlElement(name = "Ustrd")
        private String unstructured; // Content
    }
}

