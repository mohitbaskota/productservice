package com.myretail.productservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public class ProductItem {

    @Id
    private Long id;
    private Price currentPrice;

    public ProductItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Price currentPrice) {
        this.currentPrice = currentPrice;
    }
}
