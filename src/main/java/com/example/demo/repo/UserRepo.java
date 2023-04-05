package com.example.demo.repo;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

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
        return "User Deleted!";
    }

    public String update(String email, User user) {
        dynamoDBMapper.save(user,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("email",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(email)
                                )));
        return email;
    }
}