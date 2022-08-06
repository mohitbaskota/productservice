package com.myretail.productservice.services.externalsourceservices;

import com.myretail.productservice.dto.Product;

public interface ProductDataSourceService {

    /**
     * This method the the interface to get the product details from different data source.
     * @param productId - product id of the product
     * @return returns the Product model hydrated with the details.
     */
    Product getProductDetailsById(Long productId);

}
