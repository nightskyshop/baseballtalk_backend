package com.example.test.DTO;

import com.example.test.Entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String image;
    private String introduce;
    private String team;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setImage(image);
        user.setIntroduce(introduce);
        user.setTeam(team);

        return user;
    }
}
