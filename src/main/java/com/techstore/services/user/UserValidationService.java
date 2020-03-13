package com.techstore.services.user;

import com.techstore.dto.NewUserDto;
import com.techstore.dto.UserDto;

interface UserValidationService {
    void registrationValidation(final NewUserDto newUser);
    void profileValidation(final UserDto user);
}
