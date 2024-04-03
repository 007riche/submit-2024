package com.todo.company.hai704.restapi.service.bookingpublisherservice.configuration;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Properties;


public class ConfigurationBackupService {

    public void backupConfigurationToFile(String sourceFilePath, String destinationFilePath) {
        try {
            // Read content from the source file (application.properties)
            System.out.println("Source: "+sourceFilePath);
            System.out.println("Destination: "+destinationFilePath);
            Path sourcePath = new ClassPathResource(sourceFilePath).getFile().toPath();
            String content = readFromFile(sourcePath);

            // Write content to the destination file
            Path destinationPath = Path.of(destinationFilePath);
            writeToFile(destinationPath, content);
        } catch (IOException e) {
            System.err.println("Failed to backup configuration to file");
            e.printStackTrace();
        }
    }

    public void backupConfigurationToFile(Properties appDataSourcesProperties, String destinationFilePath) {

        try {
            // Read content from the source file (application.properties)
            String content ="";

            // Write content to the destination file
            Path destinationPath = Path.of(destinationFilePath);
            writeToFile(destinationPath, content);
            for (Map.Entry<Object, Object> entry : appDataSourcesProperties.entrySet()) {

                content=entry.getKey()+"="+entry.getValue();
                System.out.println("PropertyLine: " + entry.getKey() + "=" + entry.getValue());
                System.out.println("PropertyLine: " + content);
                writeToEndOfFile(destinationPath, content);

            }

        } catch (IOException e) {
            System.err.println("Failed to backup configuration to file");
            e.printStackTrace();
        }
    }

    private String readFromFile(Path filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }

    private void writeToFile(Path filePath, String content) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(content);
        }
    }
    private void writeToEndOfFile(Path filePath, String content) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath,  StandardOpenOption.CREATE, StandardOpenOption.APPEND /*TRUNCATE_EXISTING */)) {
            writer.write(content);
            writer.newLine();
        }
    }
}
