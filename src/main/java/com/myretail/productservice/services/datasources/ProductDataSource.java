package com.myretail.productservice.services.datasources;

import com.myretail.productservice.models.Product;

public interface ProductDataSource {

    /**
     * This method the the interface to get the product details from different data source.
     * @param productId - product id of the product
     * @return returns the Product model hydrated with the details.
     */
    Product getProductDetails(Long productId);

}
