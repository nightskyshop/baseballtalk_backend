package com.example.baseballtalk.DTO;

import com.example.baseballtalk.Entity.HitterDataEntity;
import com.example.baseballtalk.Entity.HitterEntity;
import com.example.baseballtalk.Entity.PitcherDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostResponseDTO {
    private int id;
    private String title;
    private String content;
    private TeamSmallDTO team;
    private String category;
    private UserResponseDTO author;
    private int like_count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<HitterDataEntity> hitterList;
    private List<PitcherDataEntity> pitcherList;

    public PostResponseDTO(
            int id,
            String title,
            String content,
            TeamSmallDTO team,
            String category,
            UserResponseDTO author,
            int like_count,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<HitterDataEntity> hitterList,
            List<PitcherDataEntity> pitcherList
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.team = team;
        this.category = category;
        this.author = author;
        this.like_count = like_count;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.hitterList = hitterList;
        this.pitcherList = pitcherList;
    }
}