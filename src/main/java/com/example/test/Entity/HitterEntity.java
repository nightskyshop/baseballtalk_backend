package com.example.test.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HitterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private TeamEntity team;

    @Builder
    public HitterEntity(){}
}
