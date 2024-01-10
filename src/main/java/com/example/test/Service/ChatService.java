package com.example.test.Service;

import com.example.test.DTO.ChatDTO;
import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.ChatRepository;
import com.example.test.Repository.PostRepository;
import com.example.test.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private ChatRepository repository;
    @Autowired
    private UserRepository user_repository;

    @Autowired
    private PostRepository post_repository;

    public Page<ChatDTO> getChatbyPost(int pageNo, int post_id) {
        PostEntity post = post_repository.findById(post_id);

        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ChatEntity> chatbyPostPage = repository.findByPost(pageRequest, post);
        List<ChatDTO> dtos = chatbyPostPage.getContent().stream()
                .map(entity -> new ChatDTO(
                        entity.getId(),
                        entity.getContent(),
                        entity.getAuthor().getId(),
                        entity.getPost().getId()))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, chatbyPostPage.getTotalElements());
    }

    public Page<ChatDTO> getChatbyUser(int pageNo, int user_id) {
        UserEntity author = user_repository.findById(user_id);

        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ChatEntity> chatbyUserPage = repository.findByAuthor(pageRequest, author);
        List<ChatDTO> dtos = chatbyUserPage.getContent().stream()
                .map(entity -> new ChatDTO(
                        entity.getId(),
                        entity.getContent(),
                        entity.getAuthor().getId(),
                        entity.getPost().getId()))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, chatbyUserPage.getTotalElements());
    }

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