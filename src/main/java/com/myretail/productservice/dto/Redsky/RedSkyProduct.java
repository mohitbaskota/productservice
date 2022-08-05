package com.myretail.productservice.dto.Redsky;

import java.util.Objects;

public class RedSkyProduct {

    private String tcin;
    private Item item;

    public RedSkyProduct() {
    }

    public String getTcin() {
        return tcin;
    }

    public void setTcin(String tcin) {
        this.tcin = tcin;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RedSkyProduct)) return false;
        RedSkyProduct that = (RedSkyProduct) o;
        return Objects.equals(getTcin(), that.getTcin()) &&
                Objects.equals(getItem(), that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTcin(), getItem());
    }
}
