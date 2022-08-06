package com.myretail.productservice.models;

import java.util.Objects;

public class Price {
    private float value;
    private String currency;

    public Price() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price = (Price) o;
        return Float.compare(price.getValue(), getValue()) == 0 &&
                Objects.equals(getCurrency(), price.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getCurrency());
    }
}
