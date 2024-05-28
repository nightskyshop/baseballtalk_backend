package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HitterResponseDTO {
    private int id;
    private String name;
    private int age;
    private int height;
    private int weight;
    private String image;
    private int avg;
    private int slg;
    private int obp;
    private int ops;
    private int game;
    private int hit;
    private int secondHit;
    private int thirdHit;
    private int homeRun;
    private int rbi;
    private TeamSmallDTO team;

    public HitterResponseDTO(
            int id,
            String name,
            int age,
            int height,
            int weight,
            String image,
            int avg,
            int slg,
            int obp,
            int ops,
            int game,
            int hit,
            int secondHit,
            int thirdHit,
            int homeRun,
            int rbi,
            TeamSmallDTO team
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.image = image;
        this.avg = avg;
        this.slg = slg;
        this.obp = obp;
        this.ops = ops;
        this.game = game;
        this.hit = hit;
        this.secondHit = secondHit;
        this.thirdHit = thirdHit;
        this.homeRun = homeRun;
        this.rbi = rbi;
        this.team = team;
    }
}
