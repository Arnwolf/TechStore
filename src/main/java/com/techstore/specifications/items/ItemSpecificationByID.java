package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

import java.util.Collection;

public class ItemSpecificationByID extends ItemSpecification implements SqlSpecification {
    private Collection<String> itemsIds;

    public ItemSpecificationByID(final Collection<String> itemsIds) {
        this.itemsIds = itemsIds;
    }

    @Override
    public String toSql() {
        return String.format(baseQuery + "WHERE i.id IN (%s)", itemsIds.isEmpty() ?
                0 : String.join(",", itemsIds));
    }
}
