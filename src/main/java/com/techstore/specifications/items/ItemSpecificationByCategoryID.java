package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

public class ItemSpecificationByCategoryID extends ItemSpecification implements SqlSpecification {
    private String categoryID;

    public ItemSpecificationByCategoryID(final String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toSql() {
        return String.format(baseQuery +
                "WHERE category_id=%s", categoryID);
    }
}
