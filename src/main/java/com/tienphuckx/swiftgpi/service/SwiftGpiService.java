package com.tienphuckx.swiftgpi.service;

import com.tienphuckx.swiftgpi.dto.UserTransactionRequestFull;
import com.tienphuckx.swiftgpi.swift.Pacs008Message;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SwiftGpiService {

    private final FileService fileService;

    public SwiftGpiService(FileService fileService) {
        this.fileService = fileService;
    }

    public String buildPacs008(UserTransactionRequestFull transaction) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Pacs008Message.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Pacs008Message pacs008 = new Pacs008Message(transaction);
            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(pacs008, xmlWriter);

            // Tạo tên file dựa trên timestamp + transactionId
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = "pacs008_" + transaction.getTransactionId() + "_" + timestamp + ".xml";

            // Gọi FileService để lưu file
            fileService.saveToFile(fileName, xmlWriter.toString());

            return xmlWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("Error building pacs.008 XML", e);
        }

    }
}
