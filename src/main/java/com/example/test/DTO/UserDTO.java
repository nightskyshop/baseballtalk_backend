package com.example.test.DTO;

import com.example.test.Entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class UserDTO {

    private int id;
    private String username;
    private String email;
    private String password;
    private String image;
    private String introduce;
    private String team;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        UserEntity user = new UserEntity();
        if (this.id != 0) {
            user.setId(this.id);
        }
        user.setUsername(username);
        user.setEmail(email);
        System.out.println(password);
        System.out.println(passwordEncoder.encode(password));
        user.setPassword(passwordEncoder.encode(password));
        user.setImage(image);
        user.setIntroduce(introduce);
        user.setTeam(team);

        return user;
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public UserDTO(int id, String username, String email, String password, String image, String introduce, String team) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.introduce = introduce;
        this.team = team;
    }
}
