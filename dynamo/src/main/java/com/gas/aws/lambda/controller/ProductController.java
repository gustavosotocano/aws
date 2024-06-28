package com.gas.aws.lambda.controller;

import com.gas.aws.lambda.entity.Product;
import com.gas.aws.lambda.repository.ProductoRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductoRepository productoRepository;

    public ProductController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productoRepository.save(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productoRepository.findById(id).orElse(null);
    }

    @PutMapping ("/{id}")
    public Product updateProduct(@PathVariable String id,@RequestBody Product product) {
        var produc= productoRepository.findById(id).orElse(null);

        produc.setName(product.getName());
        produc.setDescription(product.getDescription());
        produc.setPrice(product.getPrice());
        return productoRepository.save(produc);
    }

    @GetMapping
    public Iterable<Product> getProducts() {
        return productoRepository.findAll();
    }
}
