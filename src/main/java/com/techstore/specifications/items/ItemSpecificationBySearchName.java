package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

public class ItemSpecificationBySearchName extends ItemSpecification implements SqlSpecification {
    private String itemName;

    public ItemSpecificationBySearchName(final String itemName) {
        super();
        this.itemName = itemName;
    }

    @Override
    public String toSql() {
        return String.format(baseQuery +
                "WHERE CONCAT(i.Manufacturer, ' ', i.Name) LIKE '%s%%' OR " +
                "CONCAT(i.Manufacturer, ' ', i.Name) LIKE '%%%s'", itemName, itemName);
    }
}
