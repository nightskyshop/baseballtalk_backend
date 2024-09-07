package com.example.baseballtalk.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PitcherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private int age;
    private int height;
    private int weight;
    private String image;
    private float era;
    private int game;
    private float inning;
    private int win;
    private int lose;
    private int save;
    private int hold;
    private float whip;
    private boolean ranked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private TeamEntity team;

    @OneToMany(mappedBy = "pitcher", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PitcherChatEntity> chat = new ArrayList<>();

    @Builder
    public PitcherEntity(){}
}