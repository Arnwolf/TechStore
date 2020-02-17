package com.techstore.providers;

import com.techstore.components.Encoder;
import com.techstore.entities.User;
import com.techstore.services.UsersService;

import javax.validation.constraints.NotNull;

public class AuthProvider {
    private UsersService usersService;
    private Encoder encoder;

    public AuthProvider(@NotNull final Encoder encoder) {
        this.usersService = UsersService.getInstance();
        this.encoder = encoder;
    }

    public String authUser(final String email, final String pass) throws RuntimeException {
        User matchedUser = usersService.loadUserByEmail(email);

        if (matchedUser != null) {
            if (!encoder.isPasswordsMatch(pass, matchedUser.getPass()))
                throw new RuntimeException("Wrong credentials!");
            else {
                final String hashedId = encoder.hashUserId(matchedUser.getID());
                usersService.updateUserSessionId(matchedUser.getID(), hashedId);
                return hashedId;
            }
        } else
            throw new RuntimeException("There is no user with this email!");
    }
}
