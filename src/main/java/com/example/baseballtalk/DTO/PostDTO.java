package com.example.baseballtalk.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class PostDTO {
    private String title;
    private String content;
    private int team;
    private String category;
    private int author;
    private ArrayList<Integer> hitterList;
    private ArrayList<Integer> pitcherList;

    public PostDTO (String title, String content, int team, String category, int author, ArrayList<Integer> hitterList, ArrayList<Integer> pitcherList) {
        this.title = title;
        this.content = content;
        this.team = team;
        this.category = category;
        this.author = author;
        this.hitterList = hitterList;
        this.pitcherList = pitcherList;
    }
}