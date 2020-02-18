package com.techstore.entities;

public class OrderedItem extends Item {
    private String orderId;
    private Integer quantity;

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
