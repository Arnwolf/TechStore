package com.techstore.services.user;

import com.techstore.dto.NewUserDto;
import com.techstore.dto.UserDto;
import com.techstore.entities.User;


public interface UserService {
    void register(final NewUserDto user);
    UserDto getUserProfile(final String userHashedId);
    void updateProfile(final UserDto user);

    User findByHashedId(final String hashedID);
    User findByEmail(final String email);
    User findById(final int userId);

    void updateToken(final int userId, final String hashedId);

    boolean existsByEmail(final String email);
    boolean existsByName(final String name);
}
