package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponseDTO {
    private int id;
    private String content;
    private UserResponseDTO author;
    private int post;

    public ChatResponseDTO(int id, String content, UserResponseDTO author, int post) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.post = post;
    }
}
