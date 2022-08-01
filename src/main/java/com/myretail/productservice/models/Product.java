package com.myretail.productservice.models;

import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private Currency current_price;

    public Product(Long id, String name, Currency current_price) {
        this.id = id;
        this.name = name;
        this.current_price = current_price;
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

    public Currency getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Currency current_price) {
        this.current_price = current_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                getCurrent_price().equals(product.getCurrent_price());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCurrent_price());
    }
}
