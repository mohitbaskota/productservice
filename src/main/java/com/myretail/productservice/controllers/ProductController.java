package com.myretail.productservice.controllers;

import com.myretail.productservice.exceptions.ServiceException;
import com.myretail.productservice.dto.Product;
import com.myretail.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{productId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long productId) {
        if (product == null || product.getId() == null || !product.getId().equals(productId)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request. Please check that request format is correct.");
            return null;
        } else {

            return productService.updateProduct(product);
        }
    }
}
