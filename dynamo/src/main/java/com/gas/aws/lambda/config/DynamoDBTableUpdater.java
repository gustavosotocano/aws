package com.gas.aws.lambda.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;

public class DynamoDBTableUpdater {

    private final AmazonDynamoDB dynamoDB;

    public DynamoDBTableUpdater(AmazonDynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    public void updateTable() {
        try {
            UpdateTableRequest request = new UpdateTableRequest()
                    .withTableName("Product")
                    .withAttributeDefinitions(
                            new AttributeDefinition("price", ScalarAttributeType.N))  // Nuevo atributo
                    .withGlobalSecondaryIndexUpdates(new GlobalSecondaryIndexUpdate()
                                                             .withCreate(new CreateGlobalSecondaryIndexAction()
                                                                                 .withIndexName("PriceIndex")
                                                                                 .withKeySchema(new KeySchemaElement("price", KeyType.HASH))
                                                                                 .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L))
                                                                                 .withProjection(new Projection().withProjectionType(ProjectionType.ALL))));

            UpdateTableResult result = dynamoDB.updateTable(request);
            System.out.println("Table updated successfully: " + result.getTableDescription().getTableName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "http://localhost:8000", "us-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("ASW3454ERRT", "your_secret_key")))
                .build();

        DynamoDBTableUpdater updater = new DynamoDBTableUpdater(dynamoDB);
        updater.updateTable();
    }
}
