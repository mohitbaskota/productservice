package com.myretail.productservice.services.databaseservices;

import com.myretail.productservice.models.ProductItem;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoDBService implements DatabaseService {

    private final MongoTemplate mongoTemplate;

    public MongoDBService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ProductItem getProductById(Long id) {
        return mongoTemplate.findById(id, ProductItem.class);
    }
}
