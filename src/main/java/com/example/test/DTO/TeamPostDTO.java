package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamPostDTO {
    private int id;
    private String teamname;

    public TeamPostDTO(int id, String teamname) {
        this.id = id;
        this.teamname = teamname;
    }
}
