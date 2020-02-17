package com.techstore.providers;

import com.techstore.components.Encoder;
import com.techstore.entities.User;
import com.techstore.services.SubscriptionService;
import com.techstore.services.UsersService;

import javax.validation.constraints.NotNull;

public class RegistrationProvider {
    private UsersService usersService;
    private Encoder encoder;

    public RegistrationProvider(@NotNull final Encoder encoder) {
        this.usersService = UsersService.getInstance();
        this.encoder = encoder;
    }

    public void register(final User newUser, final String repeatPass) throws RuntimeException {
        if (newUser.getPass().isEmpty() || repeatPass.isEmpty())
            throw new RuntimeException("Fill the passwords field!");
        else if (newUser.getName().isEmpty())
            throw new RuntimeException("Fill user name!");
        else if (newUser.getPass().length() < 6)
            throw new RuntimeException("Password must be more then 6 symbols!");
        else if (!newUser.getPass().equals(repeatPass))
            throw new RuntimeException("Passwords are not equals!");
        else if (newUser.getEmail().isEmpty())
            throw new RuntimeException("Fill the email field!");
        else if (usersService.loadUserByEmail(newUser.getEmail()) != null)
            throw new RuntimeException("There is already registered user with same email!");
        else if (usersService.loadUserByName(newUser.getName()) != null)
            throw new RuntimeException("There is already registered user with same name!");
        else {
            newUser.setPass(encoder.hashPassword(newUser.getPass()));
            usersService.registerUser(newUser);

            SubscriptionService subscriptionService = SubscriptionService.getInstance();
            subscriptionService.unsubscribe(newUser.getEmail());
        }
    }
}
