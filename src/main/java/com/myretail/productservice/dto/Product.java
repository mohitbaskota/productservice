package com.myretail.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myretail.productservice.models.ProductItem;

import java.util.Objects;

/**
 * Product model which represents a product and includes the fields that are
 * exposed to the REST endpoints.
 */
public class Product {

    // Product Id
    private Long id;

    // product Name
    private String name;

    // Current price of the product
    @JsonProperty("current_price")
    private Currency currentPrice;

    public Product() {
    }

    public Product(Long id, String name, Currency currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public static Product fromProductItem(ProductItem productItem) {
        Product product = new Product();
        if (productItem == null)
            return product;
        product.setId(productItem.getId());
        product.setName(productItem.getName());
        Currency currency = new Currency();
        if (productItem.getCurrentPrice() != null) {
            currency.setValue(productItem.getCurrentPrice().getValue());
            currency.setCurrencyCode(productItem.getCurrentPrice().getCurrency());
            product.setCurrentPrice(currency);
        }
        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Currency currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                getCurrentPrice().equals(product.getCurrentPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCurrentPrice());
    }
}
