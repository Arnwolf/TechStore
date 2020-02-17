package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

public class ItemSpecificationByPriceRange extends ItemSpecification implements SqlSpecification {
    private int lowerBound;
    private int upperBound;

    public ItemSpecificationByPriceRange(final int lowerBound, final int upperBound) {
        if (lowerBound > 0 && upperBound > 0) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    @Override
    public String toSql() {
        return String.format(baseQuery +
                "WHERE (Price-Discount)>=%d AND (Price-Discount)<=%d ", lowerBound, upperBound);
    }
}
