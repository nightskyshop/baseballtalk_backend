package com.example.test.Service;

import com.example.test.DTO.ChatDTO;
import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.ChatRepository;
import com.example.test.Repository.PostRepository;
import com.example.test.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ChatService {
    @Autowired
    private ChatRepository repository;
    @Autowired
    private UserRepository user_repository;

    @Autowired
    private PostRepository post_repository;

    public void createChat(ChatDTO dto) {
        ChatEntity entity = new ChatEntity();
        entity.setContent(dto.getContent());

        UserEntity user = user_repository.findById(dto.getAuthor());
        entity.setAuthor(user);
        PostEntity post = post_repository.findById(dto.getPost());
        entity.setPost(post);

        repository.save(entity);
    }

    public void updateChat(int id, ChatDTO dto) {
        ChatEntity entity = repository.findById(id);
        entity.setContent(dto.getContent());
        repository.save(entity);
    }

    public void deleteChat(int id) {
        repository.deleteById(id);
    }
}