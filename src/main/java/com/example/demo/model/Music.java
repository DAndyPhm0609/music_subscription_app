package com.example.demo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    @DynamoDBHashKey
    private String title;

    @DynamoDBAttribute
    private String artist;

    @DynamoDBAttribute
    private int year;

    @DynamoDBAttribute
    private String web_url;

    @DynamoDBAttribute
    private String img_url;
}
