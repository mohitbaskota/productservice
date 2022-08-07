package com.myretail.productservice.services.databaseservices;

import com.myretail.productservice.dto.Product;
import com.myretail.productservice.models.ProductItem;

public interface DatabaseService {

    ProductItem getProductById(Long id);

    ProductItem saveProduct(ProductItem productItem);
}
