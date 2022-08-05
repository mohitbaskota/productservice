package com.myretail.productservice.dto;

import java.util.Objects;

/**
 * Currency model that represents a general currency structure that will be
 * exposed to the REST endpoints.
 */
public class Currency {

    // numeric value of the currency
    private float value;

    // The ISO code of the currency. eg. USD, CAD, AUD etc..
    private String currencyCode;

    public Currency() {
    }

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
