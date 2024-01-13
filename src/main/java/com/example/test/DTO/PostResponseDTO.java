package com.example.test.DTO;

import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class PostResponseDTO {
    private int id;
    private String title;
    private String content;
    private String team;
    private String category;
    private UserResponseDTO author;
    private int like_count;

    public PostResponseDTO(int id, String title, String content, String team, String category, UserResponseDTO author, int like_count) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.team = team;
        this.category = category;
        this.author = author;
        this.like_count = like_count;
    }
}