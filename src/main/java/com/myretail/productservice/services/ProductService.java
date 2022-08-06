package com.myretail.productservice.services;

import com.myretail.productservice.dto.Currency;
import com.myretail.productservice.exceptions.ServiceException;
import com.myretail.productservice.dto.Product;
import com.myretail.productservice.models.ProductItem;
import com.myretail.productservice.services.databaseservices.DatabaseService;
import com.myretail.productservice.services.databaseservices.MongoDBService;
import com.myretail.productservice.services.externalsourceservices.ProductDataSourceService;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDataSourceService productDataSource;
    private final DatabaseService databaseService;

    public ProductService(ProductDataSourceService productDataSource,
                          MongoDBService mongoDBService) {
        this.productDataSource = productDataSource;
        this.databaseService = mongoDBService;
    }

    public Product getProductById(Long productId) {

        try {
            // fetch the product information from product data source (gives name)
            Product product = productDataSource.getProductDetailsById(productId);

            // fetch the product from the database if it exists
            ProductItem dbProduct = databaseService.getProductById(productId);

            // we have found the product in the database and has the price
            if (dbProduct != null && dbProduct.getCurrentPrice() != null) {
                Currency currency = new Currency();
                currency.setValue(dbProduct.getCurrentPrice().getValue());
                currency.setCurrencyCode(dbProduct.getCurrentPrice().getCurrency());
                product.setCurrentPrice(currency);
            }
            return product;
        } catch (ServiceException e) {
            //todo log
            throw e;
        }
    }
}
