package com.myretail.productservice.models.Redsky;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Item {

    @JsonProperty("product_description")
    private ProductDescription productDescription;

    public Item() {
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getProductDescription(), item.getProductDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductDescription());
    }
}
