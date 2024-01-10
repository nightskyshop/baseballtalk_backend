package com.example.test.Repository;

import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository  extends JpaRepository<ChatEntity, Integer> {
    ChatEntity findById(int id);
    Page<ChatEntity> findByPost(PageRequest pageRequest, PostEntity post);
    Page<ChatEntity> findByAuthor(PageRequest pageRequest, UserEntity author);
    void deleteById(int id);
}
