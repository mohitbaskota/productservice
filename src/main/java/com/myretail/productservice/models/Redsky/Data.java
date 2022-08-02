package com.myretail.productservice.models.Redsky;

public class Data {

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
