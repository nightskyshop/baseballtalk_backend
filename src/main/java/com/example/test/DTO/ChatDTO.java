package com.example.test.DTO;

import com.example.test.Entity.ChatEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {
    private int id;
    private String content;
    private int author;
    private int post;

    public ChatDTO(int id, String content, int author, int post) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.post = post;
    }
}
