package com.techstore.providers;


import com.techstore.components.Encoder;
import com.techstore.entities.User;
import com.techstore.services.SubscriptionService;
import com.techstore.services.UsersService;


public class RegistrationProvider {
    public static void register(final User newUser, final String repeatPass) throws RuntimeException {
        UsersService usersService = UsersService.getInstance();

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
            newUser.setPass(new Encoder().hashPassword(newUser.getPass()));

            SubscriptionService.getInstance().unsubscribe(newUser.getEmail());
            usersService.addUser(newUser);
        }
    }
}
