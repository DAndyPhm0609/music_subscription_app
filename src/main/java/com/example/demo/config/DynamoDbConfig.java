package com.example.demo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {

    @Value("${aws.dynamodb.accessKey}")
    private String dynamodbAccessKey;

    @Value("${aws.dynamodb.secretKey}")
    private String dynamodbSecretKey;

    @Value("${aws.dynamodb.endpoint}")
    private String dynamodbEndpoint;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    @Bean
    public AmazonDynamoDB buildAmazonDynamoDB() {
        System.out.println(dynamodbAccessKey);
        System.out.println(dynamodbSecretKey);
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                dynamodbEndpoint,
                                awsRegion
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        dynamodbAccessKey,
                                        dynamodbSecretKey
                                )
                        )
                )
                .build();
    }

}