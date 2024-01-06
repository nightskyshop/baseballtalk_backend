package com.example.test.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String team;
    private String category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private UserEntity author;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ChatEntity> chat = new ArrayList<>();

    @Builder
    public PostEntity(){}
}