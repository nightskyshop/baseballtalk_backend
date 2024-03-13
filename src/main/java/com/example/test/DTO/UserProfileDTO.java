package com.example.test.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserProfileDTO {
    private String username;
    private String email;
    private String image;
    private String introduce;

    public UserProfileDTO(String username, String email, String image, String introduce) {
        this.username = username;
        this.email = email;
        this.image = image;
        this.introduce = introduce;
    }
}