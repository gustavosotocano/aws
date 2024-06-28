package com.gas.aws.lambda.repository;

import com.gas.aws.lambda.entity.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductoRepository extends CrudRepository<Product, String> {
}
