package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String image;
    private String introduce;
    private String team;
    private boolean isdeleted;
    private boolean isadmin;
}
