package com.example.restexcersise.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUserByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        String loginResourceUrl = "https://api.github.com/users/" + login;
        GitHubResponse gitHubResponse = restTemplate.getForObject(loginResourceUrl, GitHubResponse.class);
        UserResponse userResponse = new UserResponse();
        if (gitHubResponse != null) {
            userResponse.setId(gitHubResponse.getId());
            userResponse.setLogin(gitHubResponse.getLogin());
            userResponse.setName(gitHubResponse.getName());
            userResponse.setType(gitHubResponse.getType());
            userResponse.setAvatarURL(gitHubResponse.getAvatarUrl());
            userResponse.setCreatedAt(gitHubResponse.getCreatedAt());
            if (gitHubResponse.getFollowers() != 0) {
                userResponse.setCalculations(6 / ((float)gitHubResponse.getFollowers() * (2 + (float)gitHubResponse.getPublicRepos())));
            }
            System.out.println(gitHubResponse);
        }
        return userResponse;
    }

    public void addUser(UserRequest userRequest) {
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
