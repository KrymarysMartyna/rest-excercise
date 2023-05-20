package com.example.restexcersise.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubResponse implements Serializable {
    private String login;
    private long id;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private int followers;
    private String type;
    private String name;
    @JsonProperty("public_repos")
    private int publicRepos;
    @JsonProperty("created_at")
    private String createdAt;
}
