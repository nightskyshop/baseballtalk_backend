package com.example.baseballtalk.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PitcherDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;
    private float era;
    private float inning;
    private int win;
    private float whip;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private PostEntity post;

    @Builder
    public PitcherDataEntity(){}
}
