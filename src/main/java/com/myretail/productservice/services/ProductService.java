package com.myretail.productservice.services;

import com.myretail.productservice.dto.Currency;
import com.myretail.productservice.exceptions.ServiceException;
import com.myretail.productservice.dto.Product;
import com.myretail.productservice.models.Price;
import com.myretail.productservice.models.ProductItem;
import com.myretail.productservice.services.databaseservices.DatabaseService;
import com.myretail.productservice.services.databaseservices.MongoDBService;
import com.myretail.productservice.services.externalsourceservices.ProductDataSourceService;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDataSourceService productDataSourceService;
    private final DatabaseService databaseService;

    public ProductService(ProductDataSourceService productDataSourceService,
                          MongoDBService mongoDBService) {
        this.productDataSourceService = productDataSourceService;
        this.databaseService = mongoDBService;
    }

    public Product getProductById(Long productId) {

        try {
            // fetch the product information from product data source (gives name)
            Product product = productDataSourceService.getProductDetailsById(productId);

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

    public Product updateProduct(Product product) {
        if (product == null) return null;
        ProductItem productItem = databaseService.getProductById(product.getId());
        if (productItem == null) {
            productItem = new ProductItem();
            productItem.setName(product.getName());
            productItem.setId(product.getId());
        }
        Price price = new Price();
        price.setValue(product.getCurrentPrice().getValue());
        price.setCurrency(product.getCurrentPrice().getCurrencyCode());
        productItem.setCurrentPrice(price);
        productItem = databaseService.saveProduct(productItem);
        return Product.fromProductItem(productItem);
    }
}
