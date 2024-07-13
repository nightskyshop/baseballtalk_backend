package com.example.baseballtalk.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDTO {
    private String title;
    private String content;
    private int team;
    private String category;
    private int author;

    public PostDTO (String title, String content, int team, String category, int author) {
        this.title = title;
        this.content = content;
        this.team = team;
        this.category = category;
        this.author = author;
    }
}