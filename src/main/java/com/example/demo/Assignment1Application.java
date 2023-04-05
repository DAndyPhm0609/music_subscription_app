package com.example.demo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.example.demo.model.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.example.demo.repo.MusicRepository"})
public class Assignment1Application {

	static Logger logger = LoggerFactory.getLogger(Assignment1Application.class);
	private static String dynamodbEndpoint = "http://localhost:8000/";

	private static String dynamodbAccessKey = "local";

	private static String awsRegion = "us-west-2";

	private static String dynamodbSecretKey = "local";

	public static void main(String[] args) {
		AmazonDynamoDB amazonDynamoDB = buildAmazonDynamoDB();
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Music.class);
		tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

		boolean created = TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);

		if (created) {
			logger.info("Created DynamoDB table for " + Music.class.getSimpleName());
		} else {
			logger.info("Table already exists for " + Music.class.getSimpleName());
		}
	}

	private static AmazonDynamoDB buildAmazonDynamoDB() {
		return AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint,awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(dynamodbAccessKey,dynamodbSecretKey)))
				.build();
	}
}
