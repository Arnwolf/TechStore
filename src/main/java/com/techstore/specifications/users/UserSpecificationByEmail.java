package com.techstore.specifications.users;

import com.techstore.specifications.SqlSpecification;

public class UserSpecificationByEmail implements SqlSpecification {
    private String email;

    public UserSpecificationByEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toSql() {
        return String.format("SELECT * FROM users WHERE email='%s'", email);
    }
}
