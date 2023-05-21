package com.example.restexcersise.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        String loginResourceUrl = "https://api.github.com/users/" + login;
        GitHubResponse gitHubResponse = restTemplate.getForObject(loginResourceUrl, GitHubResponse.class);
        //liniki powyżej są odpowiedzialne za pobranie danych z gita
        UserResponse userResponse = new UserResponse();
        if (gitHubResponse != null) {
            userResponse.setId(gitHubResponse.getId());
            userResponse.setLogin(gitHubResponse.getLogin());
            userResponse.setName(gitHubResponse.getName());
            userResponse.setType(gitHubResponse.getType());
            userResponse.setAvatarURL(gitHubResponse.getAvatarUrl());
            userResponse.setCreatedAt(gitHubResponse.getCreatedAt());
            if (gitHubResponse.getFollowers() != 0) {
                userResponse.setCalculations(6 / ((float) gitHubResponse.getFollowers() * (2 + (float) gitHubResponse.getPublicRepos())));
            }
        }
        saveLoginStatistic(login);
        return userResponse;
    }

    private void saveLoginStatistic(String login) {
        //teraz zaczynasz zapis statystyki ilosci wywowan dla danego loginu do bazy
        //są dwa przypadki: 1) nie ma jeszcze użytkownika 2) jest użytkownik, ale trzeba zaktualizować requestCount
        Optional<User> optionalUser = userRepository.findByLogin(login);
        //obslugujemy przypadek 2)
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRequestCount(user.getRequestCount() + 1);
            userRepository.save(user);
        } else { //obslugujemy przypadek 1)
            User user = new User();
            user.setLogin(login);
            user.setRequestCount(1);
            userRepository.save(user);
        }
    }
}
