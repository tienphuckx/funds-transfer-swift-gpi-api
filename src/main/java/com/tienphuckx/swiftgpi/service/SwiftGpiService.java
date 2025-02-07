package com.tienphuckx.swiftgpi.service;

import com.tienphuckx.swiftgpi.dto.UserTransactionRequestFull;
import com.tienphuckx.swiftgpi.swift.Pacs008Message;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
public class SwiftGpiService {

    public String buildPacs008(UserTransactionRequestFull transaction) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Pacs008Message.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Pacs008Message pacs008 = new Pacs008Message(transaction);
            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(pacs008, xmlWriter);

            return xmlWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("Error building pacs.008 XML", e);
        }
    }
}
