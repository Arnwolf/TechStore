package com.techstore.components.validator;

import com.techstore.dto.UserDto;
import com.techstore.services.user.UserService;
import com.techstore.services.user.UserServiceImpl;


public class ProfileValidator implements Validator<UserDto> {
    @Override
    public void validate(final UserDto userDto) {

        if (userDto.name.isEmpty())
            throw new RuntimeException("Name can not be empty!");
        else if (userDto.email.isEmpty())
            throw new RuntimeException("Email can not be empty!");
        else if (!userDto.pass.isEmpty()) {
            if (userDto.pass.trim().length() < 6)
                throw new RuntimeException("Password must contain 6 symbols!");
        } else {
            UserService userService = UserServiceImpl.getInstance();
            UserDto originalProfile = userService.getUserProfile(userDto.hashedId);

            if (!originalProfile.email.equals(userDto.email)) {
                if (userService.existsByEmail(userDto.email))
                    throw new RuntimeException("There is already registered user with same email!");
            } else if (!originalProfile.name.equalsIgnoreCase(userDto.name)) {
                if (userService.existsByName(userDto.name))
                    throw new RuntimeException("There is already registered user with same name!");
            }
        }
    }
}
