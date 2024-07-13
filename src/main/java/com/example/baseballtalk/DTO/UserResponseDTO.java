package com.example.baseballtalk.DTO;

import com.example.baseballtalk.Entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    private  int id;
    private String username;
    private String email;
    private String image;
    private String introduce;
    private String team;

    public static UserResponseDTO of(UserEntity user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .image(user.getImage())
                .introduce(user.getIntroduce())
                .team(user.getTeam())
                .build();

    }

    public UserResponseDTO(int id, String username, String email, String image, String introduce, String team) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = image;
        this.introduce = introduce;
        this.team = team;
    }
}
