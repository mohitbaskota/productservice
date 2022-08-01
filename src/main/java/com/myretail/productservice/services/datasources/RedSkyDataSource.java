package com.myretail.productservice.services.datasources;

import com.myretail.productservice.models.Product;
import org.springframework.stereotype.Component;

@Component
public class RedSkyDataSource implements ProductDataSource {

    @Override
    public Product getProductDetailsById(Long productId) {
        return null;
    }
}
