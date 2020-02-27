package com.techstore.services;

import com.techstore.entities.User;
import com.techstore.providers.RegistrationProvider;
import org.junit.Assert;
import org.junit.Test;


public class RegistrationTest {

    @Test
    public void testRegistration() {
        final String pass = "12345678";
        final String email = "Test@mail.com";

        SubscriptionService subscriptionService = SubscriptionService.getInstance();
        subscriptionService.subscribe(email);

        User newUser = new User(email,
                "RegistrationTest",
                pass,
                true);

        RegistrationProvider.register(newUser, pass);

        Assert.assertNull(subscriptionService.getSubscription(email));

        UsersService usersService = UsersService.getInstance();
        usersService.deleteUser(usersService.loadUserByEmail(newUser.getEmail()));
    }
}
