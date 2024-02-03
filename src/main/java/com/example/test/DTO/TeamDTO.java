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

    public TeamDTO(int id, String teamname, int rank_num, int game, int win, int lose, int tie, int winavg, int avg, float era) {
        this.id = id;
        this.teamname = teamname;
        this.rank_num = rank_num;
        this.game = game;
        this.win = win;
        this.lose = lose;
        this.tie = tie;
        this.winavg = winavg;
        this.avg = avg;
        this.era = era;
    }
}
