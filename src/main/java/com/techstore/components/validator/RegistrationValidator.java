package com.techstore.components.validator;

import com.techstore.dto.NewUserDto;
import com.techstore.services.user.UserServiceImpl;


public class RegistrationValidator implements Validator<NewUserDto> {

    @Override
    public void validate(final NewUserDto userDTO) {
        if (userDTO.pass.isEmpty() || userDTO.repeatedPass.isEmpty())
            throw new RuntimeException("Fill the passwords field!");
        else if (userDTO.userName.isEmpty())
            throw new RuntimeException("Fill user name!");
        else if (userDTO.pass.length() < 6)
            throw new RuntimeException("Password must be more then 6 symbols!");
        else if (!userDTO.pass.equals(userDTO.repeatedPass))
            throw new RuntimeException("Passwords are not equals!");
        else if (userDTO.email.isEmpty())
            throw new RuntimeException("Fill the email field!");
        else if (UserServiceImpl.getInstance().existsByEmail(userDTO.email))
            throw new RuntimeException("There is already registered user with same email!");
        else if (UserServiceImpl.getInstance().existsByName(userDTO.userName))
            throw new RuntimeException("There is already registered user with same name!");
    }
}
