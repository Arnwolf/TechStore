package com.techstore.specifications.wishes;

import com.techstore.specifications.SqlSpecification;

public class WishesSpecificationByUserHashedID implements SqlSpecification {
    private String userHashedId;

    public WishesSpecificationByUserHashedID(final String userHashedId) {
        this.userHashedId = userHashedId;
    }

    @Override
    public String toSql() {
        return String.format("SELECT u.id AS userId, i.* " +
                "FROM users u " +
                "JOIN users_wishes uw on u.id = uw.user_id " +
                "JOIN items i on uw.item_id = i.id " +
                "WHERE u.hashed_id='%s'", userHashedId);
    }
}

