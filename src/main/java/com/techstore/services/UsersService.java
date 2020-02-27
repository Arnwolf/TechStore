package com.techstore.services;

import com.techstore.components.UserInfoChecker;
import com.techstore.entities.Subscription;
import com.techstore.entities.User;
import com.techstore.repositories.UserRepository;
import java.util.List;


public class UsersService {
    private UserRepository userRepository = new UserRepository();

    private static UsersService instance = new UsersService();
    public static UsersService getInstance() { return instance; }

    private UsersService() { }

    public void addUser(final User user) {
        try {
            userRepository.add(user);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            throw new RuntimeException("SQL Problems");
        }
    }

    public void updateUser(final User user) {
        try {
            userRepository.update(user);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            throw new RuntimeException("SQL Problems");
        }
    }

    public void deleteUser(final User user) {
        try {
            userRepository.remove(user);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            throw new RuntimeException("SQL Problems");
        }
    }

    public User loadUserByEmail(final String email) {
        List<User> matchedUser = userRepository.findByEmail(email);
        return matchedUser.isEmpty() ? null : matchedUser.get(0);
    }

    public User loadUserByName(final String name) {
        List<User> matchedUser = userRepository.findByName(name);
        return matchedUser.isEmpty() ? null : matchedUser.get(0);
    }

    public User loadUserByHashedId(final String hashedID) {
        List<User> matchedUsers = userRepository.findByHashedId(hashedID);
        return matchedUsers.isEmpty() ? null : matchedUsers.get(0);
    }

    public void updateUserInfo(final User newInfo) {
        User updatedInfo = UserInfoChecker.updateInfo(newInfo);
        updateUser(updatedInfo);
    }
}
