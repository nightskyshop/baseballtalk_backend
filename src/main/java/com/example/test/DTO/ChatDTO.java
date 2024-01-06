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
}
