package com.techstore.services.user;

import com.techstore.components.validator.ProfileValidator;
import com.techstore.components.validator.RegistrationValidator;
import com.techstore.components.validator.Validator;
import com.techstore.dto.NewUserDto;
import com.techstore.dto.UserDto;


class UserValidationServiceImpl implements UserValidationService {
    private static final UserValidationService validationService = new UserValidationServiceImpl();
    public static UserValidationService getInstance() { return validationService; }

    private Validator<NewUserDto> registrationValidator = new RegistrationValidator();
    private Validator<UserDto> profileValidator = new ProfileValidator();

    private UserValidationServiceImpl() { }

    @Override
    public void registrationValidation(final NewUserDto newUser) { registrationValidator.validate(newUser); }

    @Override
    public void profileValidation(final UserDto newUser) { profileValidator.validate(newUser); }
}
