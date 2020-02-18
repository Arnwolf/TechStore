package com.techstore.specifications.orders;

import com.techstore.specifications.SqlSpecification;

import java.util.Collection;

public class OrderItemsSpecificationByMultipleID implements SqlSpecification {
    private Collection<String> ordersIds;

    public OrderItemsSpecificationByMultipleID(final Collection<String> ordersIds) {
        this.ordersIds = ordersIds;
    }

    @Override
    public String toSql() {
        return String.format("SELECT oi.item_id, oi.quantity, i.* " +
                        "FROM orders_items oi " +
                        "JOIN items i ON i.id=oi.item_Id " +
                        "WHERE oi.order_id IN (%s)",
                String.join(",", ordersIds));
    }
}
