package com.myretail.productservice.services;

import com.myretail.productservice.exceptions.ServiceException;
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

        try {
            Product product = productDataSource.getProductDetailsById(productId);
            // need to handle null product case
            //todo -- fetch the currency data from nosql
            return product;
        } catch (ServiceException e) {
            //todo log
            throw e;
        }
    }
}
