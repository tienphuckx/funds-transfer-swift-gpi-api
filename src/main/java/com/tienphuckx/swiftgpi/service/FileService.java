package com.tienphuckx.swiftgpi.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class FileService {

    private static final String DIRECTORY = "swift_messages/";

    public void saveToFile(String fileName, String content) {
        try {
            // Tạo thư mục nếu chưa tồn tại
            Files.createDirectories(Paths.get(DIRECTORY));

            // Ghi nội dung vào file
            File file = new File(DIRECTORY + fileName);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
            }

            System.out.println("File saved successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error saving SWIFT message file", e);
        }
    }
}
