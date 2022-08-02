package com.myretail.productservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedSkyResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public RedSkyResponse() {
    }
}

class Data {
    private RedSkyProduct product;

    public RedSkyProduct getProduct() {
        return product;
    }

    public Data() {
    }

    public Data(RedSkyProduct data) {
        this.product = data;
    }

    public void setProduct(RedSkyProduct product) {
        this.product = product;
    }
}

class RedSkyProduct {

    public RedSkyProduct() {
    }

    public String getTcin() {
        return tcin;
    }

    public void setTcin(String tcin) {
        this.tcin = tcin;
    }

    private String tcin;
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

class Item {
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
}

class ProductDescription {

    private String title;

    public ProductDescription() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
