package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

public class ItemSpecificationByID extends ItemSpecification implements SqlSpecification {
    private String itemID;

    public ItemSpecificationByID(final String itemID) {
        this.itemID = itemID;
    }

    @Override
    public String toSql() {
        return String.format(baseQuery +
                "WHERE i.id=%s", itemID);
    }
}
