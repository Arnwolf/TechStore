package com.techstore.specifications.reviews;

import com.techstore.specifications.SqlSpecification;

public class ReviewSpecificationByItemID implements SqlSpecification {
    private String ItemID;

    public ReviewSpecificationByItemID(final String ItemID) {
        this.ItemID = ItemID;
    }
    @Override
    public String toSql() {
        return String.format("SELECT ir.*, u.name " +
                "FROM item_reviews ir " +
                "JOIN users u ON u.id=ir.user_id " +
                "WHERE ir.item_id=%s", ItemID);
    }
}
