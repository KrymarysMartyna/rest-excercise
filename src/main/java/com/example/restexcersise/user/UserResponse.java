package com.example.restexcersise.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarURL;
    private String createdAt;
    private float calculations;
}
