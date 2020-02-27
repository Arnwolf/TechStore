package com.techstore.providers;

import com.techstore.components.Encoder;
import com.techstore.entities.User;
import com.techstore.services.UsersService;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

public class AuthProvider {
    private UsersService usersService;
    private Encoder encoder;
    private HttpSession session;

    public AuthProvider(@NotNull final Encoder encoder) {
        this.usersService = UsersService.getInstance();
        this.encoder = encoder;
    }

    public void setSession(final HttpSession session) {
        this.session = session;
    }

    public void authUser(final String email, final String pass) throws RuntimeException {
        User matchedUser = usersService.loadUserByEmail(email);

        if (matchedUser != null) {
            if (!encoder.isPasswordsMatch(pass, matchedUser.getPass()))
                throw new RuntimeException("Wrong credentials!");
            else {
                matchedUser.setHashedID(encoder.hashUserId(matchedUser.getID()));
                usersService.updateUser(matchedUser);

                session.setAttribute("UserID", matchedUser.getHashedID());
                session.setMaxInactiveInterval(30 * 60 * 60); // 30 hours
            }
        } else
            throw new RuntimeException("There is no user with this email!");
    }
}
