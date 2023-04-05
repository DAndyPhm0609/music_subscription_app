package com.example.demo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.example.demo.model.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.example.demo.repo.MusicRepository"})
@ComponentScan({"com.example.demo.config.DynamoDbConfig"})
public class Assignment1Application implements CommandLineRunner {

	static Logger logger = LoggerFactory.getLogger(Assignment1Application.class);

	@Autowired
	AmazonDynamoDB amazonDynamoDB;

	@Autowired
	DynamoDBMapper dynamoDBMapper;

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Music.class);
		tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

//		boolean created = TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
//
//		if (created) {
//			logger.info("Created DynamoDB table for " + Music.class.getSimpleName());
//		} else {
//			logger.info("Table already exists for " + Music.class.getSimpleName());
//		}
	}
}
