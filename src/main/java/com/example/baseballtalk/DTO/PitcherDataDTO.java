package com.example.baseballtalk.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PitcherDataDTO {
    private int id;
    private String name;
    private String image;
    private float era;
    private float inning;
    private int win;
    private float whip;

    public PitcherDataDTO(
            int id,
            String name,
            String image,
            float era,
            float inning,
            int win,
            float whip
    ) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.era = era;
        this.inning = inning;
        this.win = win;
        this.whip = whip;
    }
}
