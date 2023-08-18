package com.itg.project1.s3objectsview.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.sql.Time;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class S3Services implements IS3Services {
    @Value("${AWS_ACCESS_KEY_ID}")
    private String accessKeyId;

    @Value("${AWS_SECRET_ACCESS_KEY}")
    private String secretKey;

    @Value("${bucketName}")
    private String bucketName;
    @Value("${LOGS_BUCKET_NAME}")
    private String logsBucketName;
    @Value("${AWS_REGION}")
    private String region;
    private AmazonS3 s3;
    private final LogFileService logFileService;

    public S3Services(AmazonS3 s3, LogFileService logFileService) {
        this.s3 = s3;
        this.logFileService = logFileService;
    }

    @Override
    public List<String> getAllImagesFromS3Bucket() {
        ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(bucketName);
        List<String> objectUrls = new ArrayList<>();

        ListObjectsV2Result result;
        do {
            result = s3.listObjectsV2(request);
            List<S3ObjectSummary> objects = result.getObjectSummaries();
            for (S3ObjectSummary objectSummary : objects) {
                String objectKey = objectSummary.getKey();
                String objectUrl = s3.getUrl(bucketName, objectKey).toString();
                objectUrls.add(objectUrl);
            }
            request.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        return objectUrls;
    }
    public void uploadLogFile() {


        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKey);

        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();

        File logFile = logFileService.getLogFile();


        String key = logFile.getName() + " " + LocalDate.now();
        PutObjectRequest request = new PutObjectRequest(logsBucketName, key, logFile);
        amazonS3.putObject(request);
        System.out.println("File Uploaded Successfully: " + key);
    }
}
