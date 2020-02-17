package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

public class ItemSpecificationByPopular extends ItemSpecification implements SqlSpecification {
    private int limit;
    private int offset;

    public void setRowsRange(final int limit, final int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String toSql() {
        return String.format(baseQuery +
                "JOIN item_reviews ir ON ir.item_id=i.id " +
                "WHERE availability>0 AND (discount>0 OR ir.rating>3 OR new) " +
                "GROUP BY i.id " +
                "%s %s", limit > 0 ? "LIMIT " + limit : "", offset > 0 ? " OFFSET " + offset : "");
    }
}
