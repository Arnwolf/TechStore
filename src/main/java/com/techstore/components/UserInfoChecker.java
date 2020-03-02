package com.techstore.components;

import com.techstore.entities.User;
import com.techstore.services.UsersService;


public class UserInfoChecker {
    public static User updateInfo(final User newInfo) {
        UsersService usersService = UsersService.getInstance();

        final User originalInfo = usersService.loadUserByHashedId(newInfo.getHashedID());
        newInfo.setID(originalInfo.getID());

        if (!originalInfo.getName().equals(newInfo.getName()))
            originalInfo.setName(newInfo.getName());

        if (!newInfo.getEmail().isEmpty() && !originalInfo.getEmail().equals(newInfo.getEmail())) {
            if (usersService.loadUserByEmail(newInfo.getEmail()) != null)
                throw new RuntimeException("This email is not available!");
            else
                originalInfo.setEmail(newInfo.getEmail());
        }

        if (!newInfo.getPass().isEmpty())
            originalInfo.setPass(new Encoder().hashPassword(newInfo.getPass()));

        if (!originalInfo.getStreet().equals(newInfo.getStreet()))
            originalInfo.setStreet(newInfo.getStreet());

        if (!originalInfo.getCity().equals(newInfo.getCity()))
            originalInfo.setCity(newInfo.getCity());

        if (!originalInfo.getPhoneNumber().equals(newInfo.getPhoneNumber()))
            originalInfo.setPhoneNumber(newInfo.getPhoneNumber());

        if (!originalInfo.isSubscribed().equals(newInfo.isSubscribed()))
            originalInfo.setSubscribed(newInfo.isSubscribed());

        return originalInfo;
    }
}
