package com.example.demo.repo;

import com.amazonaws.auth.AWSCredentialsProvider;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

   AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
           .withCredentials(new ProfileCredentialsProvider("default"))
           .withRegion("us-east-1")
           .build();

   DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);
    public User save(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    
    public User getUserByEmail(String email) {
        return dynamoDBMapper.load(User.class, email);
    }
    public String delete(String email) {
        User emp = dynamoDBMapper.load(User.class, email);
        dynamoDBMapper.delete(emp);
        return "User Deleted";
    }


//    public String update(String email, User user) {
//        dynamoDBMapper.save(user,
//                new DynamoDBSaveExpression()
//                        .withExpectedEntry("email",
//                                new ExpectedAttributeValue(
//                                        new AttributeValue().withS(email)
//                                )));
//        return email;
//    }
}