package com.myretail.productservice.services;

import com.myretail.productservice.exceptions.ServiceException;
import com.myretail.productservice.dto.Product;
import com.myretail.productservice.services.externalsourceservices.ProductDataSourceService;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDataSourceService productDataSource;

    public ProductService(ProductDataSourceService productDataSource) {
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
