package com.myretail.productservice.controllers;

import com.myretail.productservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable String productId) {
        return new Product();
    }
}
