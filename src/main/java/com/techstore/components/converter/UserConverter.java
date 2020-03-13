package com.techstore.components.converter;

import com.techstore.dto.UserDto;
import com.techstore.entities.User;


public class UserConverter implements Converter<UserDto, User> {
    @Override
    public UserDto convert(User toConvert) {
        UserDto dto = new UserDto();
        dto.pass = toConvert.getPass();
        dto.email = toConvert.getEmail();
        dto.name = toConvert.getName();
        dto.street = toConvert.getStreet();
        dto.subscribe = toConvert.isSubscribed();
        dto.id = toConvert.getId();
        dto.city = toConvert.getCity();
        dto.phoneNumber = toConvert.getPhoneNumber();
        dto.hashedId = toConvert.getHashedID();

        return dto;
    }
}
