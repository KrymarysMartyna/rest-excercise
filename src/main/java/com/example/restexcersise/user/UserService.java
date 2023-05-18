package com.example.restexcersise.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getIdentifier(), user.getLogin()))
                .collect(Collectors.toList());
    }

    public User getUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public void addUser(UserRequest userRequest){
        User user = new User();
        user.setIdentifier(userRequest.getIdentifier());
        user.setLogin(userRequest.getLogin());
        user.setName(userRequest.getName());
        user.setType(userRequest.getType());
        user.setAvatarURL(userRequest.getAvatarURL());
        user.setCreationDate(userRequest.getCreationDate());
        user.setCalculations(userRequest.getCalculations());
        userRepository.save(user);
    }
}
