package com.example.restexcersise.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{login}")
    public User getUserByLogin (@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping
    public void addPlant(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
    }
}
