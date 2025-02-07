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
    <MsgId>c498ba3c-99bd-491e-b879-5e8a3f12825c</MsgId>
    <CreDtTm>2025-02-07T11:34:23.937232158</CreDtTm>
    <NbOfTxs>1</NbOfTxs>
    <CtrlSum>1000.50</CtrlSum>
    <InstrId>e2b34074-63ce-4439-9faa-c5eca95e6ec8</InstrId>
    <EndToEndId>NOTPROVIDED</EndToEndId>
    <TxId>d7f7c57e-51eb-4a5f-bc7a-c3c965e01981</TxId>
    <IntrBkSttlmAmt Ccy="USD">
        <Value>1000.50</Value>
    </IntrBkSttlmAmt>
    <ChrgBr>SHAR</ChrgBr>
    <Dbtr>
        <Nm>Fake Sender Name</Nm>
        <PstlAdr>
            <AdrLine>Fake Address Sender</AdrLine>
        </PstlAdr>
    </Dbtr>
    <DbtrAcct>
        <Id>
            <Othr>
                <Id>123456789</Id>
            </Othr>
        </Id>
    </DbtrAcct>
    <DbtrAgt>
        <BICFI>BANKXYZ123</BICFI>
    </DbtrAgt>
    <CdtrAgt/>
    <Cdtr>
        <Nm>Fake Receiver Name</Nm>
        <PstlAdr>
            <AdrLine>Fake Address Receiver</AdrLine>
        </PstlAdr>
    </Cdtr>
    <CdtrAcct>
        <Id>
            <Othr>
                <Id>987654321</Id>
            </Othr>
        </Id>
    </CdtrAcct>
    <RmtInf/>
</Document>
```

### Todo
- Authentication/Authorization
- Validate bank account
- Notification (Email, SMS) (Kafka ?)