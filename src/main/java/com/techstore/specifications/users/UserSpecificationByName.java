package com.techstore.specifications.users;

import com.techstore.specifications.SqlSpecification;

public class UserSpecificationByName implements SqlSpecification {
    private String userName;

    public UserSpecificationByName(final String userName) {
        this.userName = userName;
    }

    @Override
    public String toSql() {
        return String.format("SELECT * FROM users WHERE name='%s'", userName);
    }
}
