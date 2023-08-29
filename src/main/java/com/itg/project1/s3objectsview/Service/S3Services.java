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

    public S3Services(AmazonS3 s3) {
        this.s3 = s3;
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
        return;
    }
}
