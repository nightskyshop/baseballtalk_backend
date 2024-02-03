package com.example.test.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    @Column(length = 65555)
    private String image;
    private String introduce;
    private String team;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PostEntity> post = new ArrayList<>();

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ChatEntity> chat = new ArrayList<>();

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LikeEntity> like = new ArrayList<>();

    private String refresh_token;
//    @Builder
//    public UserEntity(String username, String email, String password, String image, String  introduce, String  team) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.image = image;
//        this.introduce = introduce;
//        this.team = team;
//    }
    @Builder
    public UserEntity() {};
}