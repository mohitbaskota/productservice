package com.myretail.productservice.services.databaseservices;


import com.myretail.productservice.models.Price;
import com.myretail.productservice.models.ProductItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
class MongoDBServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    MongoDBService mongoDBService;

    ProductItem dbProduct;

    @BeforeEach
    public void setup() {
        this.mongoDBService = new MongoDBService(mongoTemplate);
        dbProduct = new ProductItem();
        dbProduct.setId(120L);
        dbProduct.setName("Test Name");
        Price price = new Price();
        price.setCurrency("USD");
        price.setValue(123f);
    }

    @Test
    public void testIfProductIsReturned() {
        when(mongoTemplate.findById(anyLong(), eq(ProductItem.class))).thenReturn(dbProduct);
        ProductItem product = mongoDBService.getProductById(123L);
        assertThat(product).isEqualTo(dbProduct);
    }

    @Test
    public void testIfNullIsReturnedWhenNotFound() {

        when(mongoTemplate.findById(anyLong(), eq(ProductItem.class))).thenReturn(null);
        ProductItem product = mongoDBService.getProductById(123L);
        assertThat(product).isEqualTo(null);
    }

    @Test
    public void testThatProductReturnedWhenSaved() {

        when(mongoTemplate.save(dbProduct)).thenReturn(dbProduct);
        ProductItem product = mongoDBService.saveProduct(dbProduct);
        assertThat(product).isEqualTo(dbProduct);
    }
}