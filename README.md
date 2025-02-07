# funds-transfer-swift-gpi-api
Demo funds transfer: Build Swift GPI Message
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

