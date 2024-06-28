package com.gas.aws.dynamo.repository;

import com.gas.aws.dynamo.entity.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductoRepository extends CrudRepository<Product, String> {
}
