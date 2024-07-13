package com.example.baseballtalk.DTO;

import com.example.baseballtalk.Entity.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Getter
@Setter
public class UserRequestDTO {
    private String username;
    private String team;
    private String email;
    private String image;
    private String introduce;
    private String password;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        UserEntity new_user = new UserEntity();
        new_user.setUsername(username);
        new_user.setEmail(email);
        System.out.println(password);
        System.out.println(passwordEncoder.encode(password));
        new_user.setPassword(passwordEncoder.encode(password));
        new_user.setImage(image);
        new_user.setIntroduce(introduce);
        new_user.setTeam(team);
        return new_user;
    }

    public UserRequestDTO(String username, String team, String email, String image, String introduce, String password) {
        this.username = username;
        this.team = team;
        this.email = email;
        this.image = image;
        this.introduce = introduce;
        this.password = password;
    }
}