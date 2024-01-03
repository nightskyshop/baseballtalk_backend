package com.example.test.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Entity
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String team;
    private String category;
    private int author_id;
    private String author_username;
    private String author_image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity author;

    @Builder
    public PostEntity(){}
}