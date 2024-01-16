package com.example.test.Repository;

import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.LikeEntity;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    void deleteByUserAndPost(UserEntity user, PostEntity post);
    void deleteByUserAndChat(UserEntity user, ChatEntity chat);
}
