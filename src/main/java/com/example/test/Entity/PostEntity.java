package com.example.test.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@jakarta.persistence.Entity
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    @Builder
    public void Entity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void Entity() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
