package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.ChatEntity;
import com.example.baseballtalk.Entity.PostEntity;
import com.example.baseballtalk.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository  extends JpaRepository<ChatEntity, Integer> {
    Page<ChatEntity> findByPostOrderByCreatedAtDesc(PageRequest pageRequest, PostEntity post);
    Page<ChatEntity> findByAuthorOrderByCreatedAtDesc(PageRequest pageRequest, UserEntity author);
    ChatEntity findById(int id);
    void deleteById(int id);
    boolean existsById(int id);
}
