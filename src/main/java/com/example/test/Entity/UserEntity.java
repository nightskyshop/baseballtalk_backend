package com.example.test.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String image;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PostEntity> post = new ArrayList<>();

    @Builder
    public UserEntity() {}

    public void Entity() {}
}