package com.techstore.services.user;

import com.techstore.components.Encoder;
import com.techstore.components.converter.Converter;
import com.techstore.components.converter.UserConverter;
import com.techstore.dto.NewUserDto;
import com.techstore.dto.UserDto;
import com.techstore.entities.User;
import com.techstore.services.subscription.SubscriptionService;
import com.techstore.services.subscription.SubscriptionServiceImpl;
import javax.persistence.EntityNotFoundException;


public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepository();

    private final Converter<UserDto, User> converter = new UserConverter();

    private final UserValidationService validationService = UserValidationServiceImpl.getInstance();

    private final static UserServiceImpl instance = new UserServiceImpl();
    public static UserServiceImpl getInstance() { return instance; }

    private UserServiceImpl() { }


    @Override
    public void register(final NewUserDto newUserDto) {
        validationService.registrationValidation(newUserDto);

        User newUser = new User(newUserDto.email,
                newUserDto.userName,
                new Encoder().hashPassword(newUserDto.pass),
                newUserDto.isSubscribe);

        SubscriptionService subscriptionService = SubscriptionServiceImpl.getInstance();
        subscriptionService.unsubscribe(newUserDto.email);

        try {
            userRepository.add(newUser);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public UserDto getUserProfile(final String userHashedId) {
        return converter.convert(findByHashedId(userHashedId));
    }

    @Override
    public void updateProfile(final UserDto updatedUserProfile) {
        validationService.profileValidation(updatedUserProfile);

        final User user = userRepository.findByHashedId(updatedUserProfile.hashedId)
                .orElseThrow(() -> new EntityNotFoundException(updatedUserProfile.hashedId));

        if (!updatedUserProfile.pass.isEmpty())
            user.setPass(new Encoder().hashPassword(updatedUserProfile.pass));

        user.setName(updatedUserProfile.name);
        user.setEmail(updatedUserProfile.email);
        user.setCity(updatedUserProfile.city);
        user.setSubscribed(updatedUserProfile.subscribe);
        user.setStreet(updatedUserProfile.street);
        user.setPhoneNumber(updatedUserProfile.phoneNumber);

        try {
            userRepository.update(user);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void updateToken(final int userId, final String hashedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(userId)));

        user.setHashedID(hashedId);

        try {
            userRepository.update(user);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public User findByHashedId(final String hashedID) {
        return userRepository.findByHashedId(hashedID)
                .orElseThrow(() -> new EntityNotFoundException(hashedID));
    }

    @Override
    public User findByEmail(final String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(email));
    }

    @Override
    public User findById(final int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No user with id: %s", userId)));
    }

    @Override
    public boolean existsByEmail(final String email) { return userRepository.existsByEmail(email); }

    @Override
    public boolean existsByName(final String name) { return userRepository.existsByName(name); }
}
