package com.example.test.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponseDTO {
    private int id;
    private String name;
    private String type;
    private int age;
    private int height;
    private int weight;
    private String image;
    private TeamSmallDTO team;

    public PlayerResponseDTO(int id, String name, String type, int age, int weight, String image, TeamSmallDTO team) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.weight = weight;
        this.image = image;
        this.team = team;
    }
}
