package com.demo.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.regions.Region;
import java.io.File;

@Component
public class CreateBucket {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucketName}")
    private String bucketName;

    public void launch()
    {
        CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .acl(BucketCannedACL.PUBLIC_READ)
//                .createBucketConfiguration(
//                        CreateBucketConfiguration.builder()
//                                .locationConstraint(Region.US_EAST_2.id())
//                                .build()
//                )
                .build();

        CreateBucketResponse response = s3Client.createBucket(bucketRequest);
    }

    public void uploadFile(final File file, String fileName)
    {

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .acl(BucketCannedACL.PUBLIC_READ.toString())
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(objectRequest, RequestBody.fromFile(file));
    }
}
