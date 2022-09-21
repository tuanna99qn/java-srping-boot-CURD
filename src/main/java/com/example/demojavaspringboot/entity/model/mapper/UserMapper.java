package com.example.demojavaspringboot.entity.model.mapper;

import com.example.demojavaspringboot.entity.User;
import com.example.demojavaspringboot.entity.model.dto.UserDto;
import com.example.demojavaspringboot.entity.model.request.CreateUserRequest;
import com.example.demojavaspringboot.entity.model.request.UpdateUserRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

public class UserMapper {
    public static UserDto toUerDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFullname(user.getFullname());
        userDto.setId(user.getId());
        userDto.setPhone(user.getPhone());
        return userDto;
    }
    public static User toUser(CreateUserRequest req) {
        User user = new User();
        user.setFullname(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);

        return user;
    }
    public List<UserDto> listUser(){

        return null;
    }
    public static User toUser(UpdateUserRequest req, int id) {
        User user = new User();
        user.setId(id);
        user.setFullname(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);

        return user;
    }
}
