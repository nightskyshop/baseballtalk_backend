package com.example.baseballtalk.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HitterDataDTO {
    private int id;
    private String name;
    private String image;
    private int avg;
    private int hit;
    private int homeRun;
    private int rbi;
    private int stolenBase;

    public HitterDataDTO(
            int id,
            String name,
            String image,
            int avg,
            int hit,
            int homeRun,
            int rbi,
            int stolenBase
    ) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.avg = avg;
        this.hit = hit;
        this.homeRun = homeRun;
        this.rbi = rbi;
        this.stolenBase = stolenBase;
    }
}
