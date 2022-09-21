package com.example.demojavaspringboot.service;

import com.example.demojavaspringboot.entity.User;
import com.example.demojavaspringboot.entity.model.dto.UserDto;
import com.example.demojavaspringboot.entity.model.mapper.UserMapper;
import com.example.demojavaspringboot.entity.model.request.CreateUserRequest;
import com.example.demojavaspringboot.entity.model.request.UpdateUserRequest;
import com.example.demojavaspringboot.exception.DuplicateRecordException;
import com.example.demojavaspringboot.exception.InternalServerException;
import com.example.demojavaspringboot.exception.NotFoundException;
import com.example.demojavaspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    private static ArrayList<User> users = new ArrayList<User>();
    public UserDto createUser(CreateUserRequest createUserRequest) {
        // Kiểm tra email đã có trong hệ thống chưa
        User check = userRepository.findByEmail(createUserRequest.getEmail());
        if (check != null) {
            throw new DuplicateRecordException("Email đã tồn tại trong hệ thống");
        }

        User user = UserMapper.toUser(createUserRequest);
        User newUser = userRepository.save(user);
        return UserMapper.toUerDto(newUser);
    }

    @Override
    public List<UserDto> getListUser() {
      List<User> users = userRepository.findAll();
      List<UserDto> rs = new ArrayList<UserDto>();
      for(User user : users){
          rs.add(UserMapper.toUerDto(user));
      }
      return rs;
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("No user found");
        }
        return UserMapper.toUerDto(user.get());
    }

    @Override
    public UserDto updateUser(UpdateUserRequest updateUserRequest, int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }

        User updateUser = UserMapper.toUser(updateUserRequest, id);
        try {
            userRepository.save(updateUser);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't update user");
        }

        return UserMapper.toUerDto(updateUser);
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't delete user");
        }
    }
}
