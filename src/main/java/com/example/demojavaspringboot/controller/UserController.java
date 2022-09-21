package com.example.demojavaspringboot.controller;

import com.example.demojavaspringboot.entity.model.dto.UserDto;
import com.example.demojavaspringboot.entity.model.request.CreateUserRequest;
import com.example.demojavaspringboot.entity.model.request.UpdateUserRequest;
import com.example.demojavaspringboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Api(value = "User APIs")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="Create a user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        UserDto user = userService.createUser(createUserRequest);
        return ResponseEntity.ok(user);
    }
    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<UserDto> result = userService.getListUser();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest req, @PathVariable int id) {
        System.out.println("req" + req);
        UserDto result = userService.updateUser(req, id);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete success");
    }
}
