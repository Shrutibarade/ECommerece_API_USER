package org.dnyanyog.controller;

import java.util.List;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    
    @GetMapping(path="/user/{id}",produces= {"application/json"})
    public UserResponse getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // ✅ POST Add new user
    @PostMapping(path="/user",produces= {"application/json"},consumes= {"application/json"})
    public UserResponse saveUser(@RequestBody UserRequest user) {
        return userService.saveUser(user);
    }

    // ✅ GET all users
    @GetMapping(path="/user",produces= {"application/json"})
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
