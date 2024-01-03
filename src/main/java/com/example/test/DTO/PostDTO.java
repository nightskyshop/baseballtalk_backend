package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private String team;
    private String category;
    private int author;
}