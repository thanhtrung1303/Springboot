package com.example.userbackend.mapper;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());

        return userDto;
    }
}
