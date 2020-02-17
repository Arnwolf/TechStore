package com.techstore.specifications.orders;

import com.techstore.specifications.SqlSpecification;

public class OrderSpecificationByID implements SqlSpecification {
    private String orderId;

    public OrderSpecificationByID(final String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toSql() {
        return String.format("SELECT * FROM orders WHERE ID=%s", orderId);
    }
}
