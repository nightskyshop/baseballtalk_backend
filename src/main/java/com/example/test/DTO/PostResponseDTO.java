package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {
    private int id;
    private String title;
    private String content;
    private TeamPostDTO team;
    private String category;
    private UserResponseDTO author;
    private int like_count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponseDTO(
            int id,
            String title,
            String content,
            TeamPostDTO team,
            String category,
            UserResponseDTO author,
            int like_count,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.team = team;
        this.category = category;
        this.author = author;
        this.like_count = like_count;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}