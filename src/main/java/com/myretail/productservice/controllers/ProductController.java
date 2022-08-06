package com.myretail.productservice.controllers;

import com.myretail.productservice.exceptions.ServiceException;
import com.myretail.productservice.dto.Product;
import com.myretail.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ExceptionHandler({ServiceException.class})
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
}
