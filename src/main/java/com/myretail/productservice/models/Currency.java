package com.myretail.productservice.models;

import java.util.Objects;

public class Currency {

    private float value;
    private String currencyCode;

    public Currency(float value, String currencyCode) {
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency currency = (Currency) o;
        return Float.compare(currency.getValue(), getValue()) == 0 &&
                getCurrencyCode().equals(currency.getCurrencyCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getCurrencyCode());
    }
}
