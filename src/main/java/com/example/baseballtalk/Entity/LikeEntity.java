package com.example.baseballtalk.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LikeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private int type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private PostEntity post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    @JsonBackReference
    private ChatEntity chat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hitter_chat_id")
    @JsonBackReference
    private HitterChatEntity hitterChat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pitcher_chat_id")
    @JsonBackReference
    private PitcherChatEntity pitcherChat;
}
