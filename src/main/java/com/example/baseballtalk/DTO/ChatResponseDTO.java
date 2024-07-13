package com.example.baseballtalk.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatResponseDTO {
    private int id;
    private String content;
    private UserResponseDTO author;
    private int post;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ChatResponseDTO(
            int id,
            String content,
            UserResponseDTO author,
            int post,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.post = post;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
