package com.techstore.services;

import com.techstore.components.Encoder;
import com.techstore.entities.User;
import com.techstore.repositories.Repository;
import com.techstore.repositories.UserRepository;
import com.techstore.specifications.SqlSpecification;
import com.techstore.specifications.users.UserSpecificationByEmail;
import com.techstore.specifications.users.UserSpecificationByHashedID;
import com.techstore.specifications.users.UserSpecificationByName;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersService {
    private Repository<User> userRepository;
    private final static Logger LOG = Logger.getLogger(UsersService.class.getName());

    private static UsersService instance = new UsersService(new UserRepository());

    public static UsersService getInstance() {
        return instance;
    }

    private UsersService(final Repository<User> userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(final User newUser) {
        addUser(newUser);
    }

    private void addUser(final User user) {
        try {
            userRepository.add(user);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private void updateUser(final User user) {
        try {
            userRepository.update(user);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private void deleteUser(final User user) {
        try {
            userRepository.remove(user);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private List<User> usersList(final SqlSpecification spec) {
        try {
            return userRepository.query(spec);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    public void updateUserSessionId(final String userId, final String hashedSessionId) {
        User user = new User();
        user.setID(userId);
        user.setHashedID(hashedSessionId);

        updateUser(user);
    }

    public User loadUserByEmail(final String email) {
        List<User> matchedUser = usersList(new UserSpecificationByEmail(email));
        return matchedUser.isEmpty() ? null : matchedUser.get(0);
    }

    public User loadUserByName(final String name) {
        List<User> matchedUser = usersList(new UserSpecificationByName(name));
        return matchedUser.isEmpty() ? null : matchedUser.get(0);
    }

    public User loadUserByHashedId(final String hashedID) {
        List<User> matchedUsers = usersList(new UserSpecificationByHashedID(hashedID));
        return matchedUsers.isEmpty() ? null : matchedUsers.get(0);
    }

    public void updateUserInfo(final Map<String, String> userParams) {
        User newUserInfo = new User();
        User originalUserInfo = loadUserByHashedId(userParams.get("userHashedId"));

        newUserInfo.setID(originalUserInfo.getID());
        boolean isChanged = false;

        if (!userParams.get("name").isEmpty() && !userParams.get("name").equals(originalUserInfo.getName())) {
            newUserInfo.setName(userParams.get("name"));
            isChanged = true;
        }

        if (!userParams.get("street").isEmpty() && !userParams.get("street").equals(originalUserInfo.getName())) {
            newUserInfo.setStreet(userParams.get("street"));
            isChanged = true;
        }

        if (!userParams.get("city").isEmpty() && !userParams.get("city").equals(originalUserInfo.getName())) {
            newUserInfo.setCity(userParams.get("city"));
            isChanged = true;
        }

        if (!userParams.get("phone").isEmpty() && !userParams.get("phone").equals(originalUserInfo.getName())) {
            newUserInfo.setPhoneNumber(userParams.get("phone"));
            isChanged = true;
        }

        if (!userParams.get("psw").isEmpty()) {
            newUserInfo.setPass(new Encoder().hashPassword(userParams.get("psw")));
            isChanged = true;
        }

        if (!userParams.get("subscribe").isEmpty()) {
            newUserInfo.setSubscribed(userParams.get("subscribe").equals("on"));
            isChanged = true;
        }

        if (!userParams.get("email").isEmpty() && !originalUserInfo.getEmail().equals(userParams.get("email"))) {
            if (loadUserByEmail(userParams.get("email")) != null)
                throw new RuntimeException("This email is already taken by another user!");
            else {
                newUserInfo.setEmail(userParams.get("email"));
                isChanged = true;
            }
        }

        if (isChanged)
            updateUser(newUserInfo);
    }
}
