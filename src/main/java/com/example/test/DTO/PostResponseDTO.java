package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class PostResponseDTO {
    private String title;
    private String content;
    private String team;
    private String category;
    private HashMap<String, String> author;
}