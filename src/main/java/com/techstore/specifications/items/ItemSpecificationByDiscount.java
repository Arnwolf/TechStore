package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

public class ItemSpecificationByDiscount extends ItemSpecification implements SqlSpecification {

    public ItemSpecificationByDiscount() { }

    @Override
    public String toSql() {
        return String.format(baseQuery +
                "WHERE i.discount>0 " +
                "LIMIT %s", SQL_LIMIT);
    }
}
