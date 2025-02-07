# funds-transfer-swift-gpi-api
## Demo funds transfer: Build Swift GPI Message

### User call api to send money
```
curl -X POST "http://localhost:8080/transactions/send" \
-H "Content-Type: application/json" \
-d '{
"senderAccount": "123456789",
"receiverAccount": "987654321",
"amount": 1000.50,
"currency": "USD",
"description": "Invoice Payment"
}'

```
### Write to file
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Document>
    <FIToFICstmrCdtTrf>
        <GrpHdr>
            <MsgId>b5b68043-14d5-43ef-a5b3-3b8a9374dbd1</MsgId>
            <CreDtTm>2025-02-07T10:28:46.094332075</CreDtTm>
        </GrpHdr>
        <CdtTrfTxInf>
            <PmtId>
                <InstrId>b5b68043-14d5-43ef-a5b3-3b8a9374dbd1</InstrId>
                <UETR>1f9c9939-fed0-415b-9d24-156a793ea6b9</UETR>
            </PmtId>
            <IntrBkSttlmAmt Ccy="USD">1000.50</IntrBkSttlmAmt>
            <Dbtr>
                <Nm>123456789</Nm>
            </Dbtr>
            <Cdtr>
                <Nm>987654321</Nm>
            </Cdtr>
        </CdtTrfTxInf>
    </FIToFICstmrCdtTrf>
</Document>
```

### Todo
- Authentication/Authorization
- Validate bank account
- Notification (Email, SMS) (Kafka ?)