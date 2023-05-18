package com.example.restexcersise.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long identifier;
    private String login;
    private String name;
    private String type;
    private String avatarURL;
    private String creationDate;
    private int calculations;
}
