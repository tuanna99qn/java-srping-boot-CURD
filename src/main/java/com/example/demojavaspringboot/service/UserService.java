package com.example.demojavaspringboot.service;

import com.example.demojavaspringboot.entity.model.dto.UserDto;
import com.example.demojavaspringboot.entity.model.request.CreateUserRequest;
import com.example.demojavaspringboot.entity.model.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDto createUser(CreateUserRequest createUserRequest);
    public List<UserDto> getListUser();

    public UserDto getUserById(int id);

    public UserDto updateUser(UpdateUserRequest createUserRequest, int id);

    public void deleteUser(int id);
}
