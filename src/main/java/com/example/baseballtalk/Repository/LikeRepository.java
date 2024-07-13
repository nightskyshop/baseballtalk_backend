package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.ChatEntity;
import com.example.baseballtalk.Entity.LikeEntity;
import com.example.baseballtalk.Entity.PostEntity;
import com.example.baseballtalk.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    void deleteByUserAndPost(UserEntity user, PostEntity post);
    void deleteByUserAndChat(UserEntity user, ChatEntity chat);
    boolean existsByUserAndPost(UserEntity user, PostEntity post);
    boolean existsByUserAndChat(UserEntity user, ChatEntity chat);
}
