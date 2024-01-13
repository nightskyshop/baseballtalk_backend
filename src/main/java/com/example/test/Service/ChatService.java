package com.example.test.Service;

import com.example.test.DTO.ChatDTO;
import com.example.test.DTO.ChatResponseDTO;
import com.example.test.DTO.UserResponseDTO;
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

    public Page<ChatResponseDTO> getChatbyPost(int pageNo, int post_id) {
        PostEntity post = post_repository.findById(post_id);

        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ChatEntity> chatbyPostPage = repository.findByPostOrderByCreatedAtDesc(pageRequest, post);
        List<ChatResponseDTO> dtos = chatbyPostPage.getContent().stream()
                .map(entity -> new ChatResponseDTO(
                        entity.getId(),
                        entity.getContent(),
                        new UserResponseDTO(
                                entity.getAuthor().getId(),
                                entity.getAuthor().getUsername(),
                                entity.getAuthor().getEmail(),
                                entity.getAuthor().getImage(),
                                entity.getAuthor().getIntroduce(),
                                entity.getAuthor().getTeam()
                        ),
                        entity.getPost().getId()))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, chatbyPostPage.getTotalElements());
    }

    public Page<ChatResponseDTO> getChatbyUser(int pageNo, int user_id) {
        UserEntity author = user_repository.findById(user_id);

        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ChatEntity> chatbyUserPage = repository.findByAuthorOrderByCreatedAtDesc(pageRequest, author);
        List<ChatResponseDTO> dtos = chatbyUserPage.getContent().stream()
                .map(entity -> new ChatResponseDTO(
                        entity.getId(),
                        entity.getContent(),
                        new UserResponseDTO(
                                entity.getAuthor().getId(),
                                entity.getAuthor().getUsername(),
                                entity.getAuthor().getEmail(),
                                entity.getAuthor().getImage(),
                                entity.getAuthor().getIntroduce(),
                                entity.getAuthor().getTeam()
                        ),
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