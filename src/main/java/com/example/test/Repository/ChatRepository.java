package com.example.test.Repository;

import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository  extends JpaRepository<ChatEntity, Integer> {
    ChatEntity findById(int id);
    void deleteById(int id);
}
