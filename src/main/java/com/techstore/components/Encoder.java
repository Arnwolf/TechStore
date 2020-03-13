package com.techstore.components;

import org.mindrot.jbcrypt.BCrypt;

public class Encoder {
    public String hashPassword(final String userPassword) {
        return BCrypt.hashpw(userPassword, BCrypt.gensalt());
    }

    public String hashUserId(final Integer userId) {
        return BCrypt.hashpw(String.valueOf(userId), BCrypt.gensalt(4));
    }

    public boolean isPasswordsMatch(final String userPassword, final String userHashedPassword) {
        return BCrypt.checkpw(userPassword, userHashedPassword);
    }
}
