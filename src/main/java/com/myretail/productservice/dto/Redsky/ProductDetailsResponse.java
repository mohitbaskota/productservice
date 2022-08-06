package com.myretail.productservice.dto.Redsky;

import java.util.Objects;

public class ProductDetailsResponse {

    private Data data;

    public ProductDetailsResponse() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetailsResponse)) return false;
        ProductDetailsResponse that = (ProductDetailsResponse) o;
        return Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }
}
