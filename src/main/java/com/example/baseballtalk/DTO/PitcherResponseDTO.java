package com.example.baseballtalk.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PitcherResponseDTO {
    private int id;
    private String name;
    private int age;
    private int height;
    private int weight;
    private String image;
    private float era;
    private int game;
    private float inning;
    private int win;
    private int lose;
    private int save;
    private int hold;
    private float whip;
    private TeamSmallDTO team;

    public PitcherResponseDTO(
            int id,
            String name,
            int age,
            int height,
            int weight,
            String image,
            float era,
            int game,
            float inning,
            int win,
            int lose,
            int save,
            int hold,
            float whip,
            TeamSmallDTO team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.image = image;
        this.era = era;
        this.game = game;
        this.inning = inning;
        this.win = win;
        this.lose = lose;
        this.save = save;
        this.hold = hold;
        this.whip = whip;
        this.team = team;
    }
}
