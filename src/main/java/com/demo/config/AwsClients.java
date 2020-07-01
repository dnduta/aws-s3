package com.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class AwsClients {

    @Value("${aws.accessKeyId}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secret;

    @Bean
    public AwsCredentialsProvider credentialsProvider() {
//        return new SystemPropertiesCredentialsProvider();
        return StaticCredentialsProvider.create(credentials());
    }

    @Bean
    public AwsBasicCredentials credentials()
    {
        return  AwsBasicCredentials.create(accessKey, secret);
    }

    @Bean
    public S3Client s3Client()
    {
        return S3Client.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(credentialsProvider())
                .build();
    }

}
