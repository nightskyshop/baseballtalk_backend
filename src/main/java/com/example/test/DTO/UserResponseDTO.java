package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private  int id;
    private String username;
    private String email;
    private String image;
    private String introduce;
    private String team;

    public UserResponseDTO(int id, String username, String email, String image, String introduce, String team) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = image;
        this.introduce = introduce;
        this.team = team;
    }
}
