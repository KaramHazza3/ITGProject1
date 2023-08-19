package com.itg.project1.s3objectsview.Config;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class CustomLoggerConfig {
    private static Logger logger = Logger.getLogger(CustomLoggerConfig.class.getName());
    private FileWriter fileWriter;

    private final S3Config s3Client;
    @Value("${LOGS_BUCKET_NAME}")
    private String bucketName;

    public CustomLoggerConfig(S3Config s3Client) {
        this.s3Client = s3Client;
        try {
            String logFileName = generateLogFileName();

            File logFile = new File(logFileName);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            fileWriter = new FileWriter(logFile, true);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error initializing custom logger", e);
        }
    }

    public void logAction(String action) {
        try {
            String logEntry = "[" + System.currentTimeMillis() + "] [INFO] " + action + System.lineSeparator();
            fileWriter.write(logEntry);
            fileWriter.flush();
            uploadLogToS3(logEntry);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing log entry", e);
        }
    }

    private void uploadLogToS3(String logEntry) {
        String s3ObjectKey = generateLogFileName();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(logEntry.length());

        s3Client.s3().putObject(new PutObjectRequest(bucketName, s3ObjectKey, new ByteArrayInputStream(logEntry.getBytes()), metadata));
    }

    private String generateLogFileName() {
        return "myapp_" + UUID.randomUUID() + ".log";
    }
}
