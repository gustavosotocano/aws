package com.gas.aws.lambda.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;

public class DynamoDBTableCreator {

    public static void main(String[] args) {
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "http://localhost:8000", "us-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("ASW3454ERRT", "your_secret_key")))
                .build();

        try {
            CreateTableRequest request = new CreateTableRequest()
                    .withTableName("Product")
                    .withKeySchema(new KeySchemaElement("id", KeyType.HASH))
                    .withAttributeDefinitions(new AttributeDefinition("id", ScalarAttributeType.S))
                    .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L));

            CreateTableResult result = dynamoDB.createTable(request);
            System.out.println("Table created successfully: " + result.getTableDescription().getTableName());

        } catch (ResourceInUseException e) {
            System.out.println("Table already exists.");
        }
    }
}
