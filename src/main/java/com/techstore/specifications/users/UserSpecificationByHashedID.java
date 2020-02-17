package com.techstore.specifications.users;

import com.techstore.specifications.SqlSpecification;

public class UserSpecificationByHashedID implements SqlSpecification {
    private String HashedID;

    public UserSpecificationByHashedID(final String hashedID) {
        this.HashedID = hashedID;
    }

    @Override
    public String toSql() {
        return String.format("SELECT * FROM users WHERE hashed_id='%s'", HashedID);
    }
}
