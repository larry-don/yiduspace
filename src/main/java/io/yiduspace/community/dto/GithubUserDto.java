package io.yiduspace.community.dto;

import lombok.Data;

@Data
public class GithubUserDto {
    private String name;
    private long id;
    private String bio;
    private String avatarUrl;
}
