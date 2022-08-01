package com.myretail.productservice.services;

import com.myretail.productservice.models.Product;
import com.myretail.productservice.services.datasources.ProductDataSource;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDataSource productDataSource;

    public ProductService(ProductDataSource productDataSource) {
        this.productDataSource = productDataSource;
    }

    public Product getProductById(Long productId) {
        return productDataSource.getProductDetails(productId);
    }
}
