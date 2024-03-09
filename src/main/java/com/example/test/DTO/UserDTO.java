package com.example.test.DTO;

import com.example.test.Entity.UserEntity;
import com.example.test.Repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDTO {
    private UserEntity user;
    private String username;
    private String email;
    private String password;
    private String image;
    private String introduce;
    private String team;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        if (user != null) {
            user.setUsername(username);
            user.setEmail(email);
            System.out.println(password);
            System.out.println(passwordEncoder.encode(password));
            user.setPassword(passwordEncoder.encode(password));
            user.setImage(image);
            user.setIntroduce(introduce);
            user.setTeam(team);
            return user;
        } else {
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
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public UserDTO(UserEntity user, String username, String email, String password, String image, String introduce, String team) {
        this.user = user;
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.introduce = introduce;
        this.team = team;
    }
}
