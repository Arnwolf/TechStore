package com.techstore.services.auth;

import com.techstore.components.Encoder;
import com.techstore.dto.AuthDto;
import com.techstore.entities.User;
import com.techstore.services.user.UserService;
import com.techstore.services.user.UserServiceImpl;


public class AuthenticationServiceImpl implements AuthenticationService {
    private Encoder encoder = new Encoder();

    private static AuthenticationService authenticationService = new AuthenticationServiceImpl();
    public static AuthenticationService getInstance() { return authenticationService; }

    private UserService userService = UserServiceImpl.getInstance();

    private AuthenticationServiceImpl() { }

    @Override
    public String auth(final AuthDto dto) {
        User matchedUser = userService.findByEmail(dto.email);

        if (!encoder.isPasswordsMatch(dto.password, matchedUser.getPass()))
            throw new RuntimeException("Wrong credentials!");
        else {
            final String hashedId = encoder.hashUserId(matchedUser.getId());

            userService.updateToken(matchedUser.getId(), hashedId);

            return hashedId;
        }
    }
}
