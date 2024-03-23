package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamSmallDTO {
    private int id;
    private String teamname;
    private String teamnameEn;

    public TeamSmallDTO(int id, String teamname, String teamnameEn) {
        this.id = id;
        this.teamname = teamname;
        this.teamnameEn = teamnameEn;
    }
}
