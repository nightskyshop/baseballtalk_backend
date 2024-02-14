package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamSmallDTO {
    private int id;
    private String teamname;

    public TeamSmallDTO(int id, String teamname) {
        this.id = id;
        this.teamname = teamname;
    }
}
