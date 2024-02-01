package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {
    private int id;
    private String teamname;
    private int rank_num;
    private int game;
    private int win;
    private int lose;
    private int tie;
    private int winavg;
    private int avg;
    private float era;
}
