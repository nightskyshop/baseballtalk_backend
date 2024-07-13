package com.example.baseballtalk.Service;

import com.example.baseballtalk.DTO.ChatDTO;
import com.example.baseballtalk.DTO.ChatResponseDTO;
import com.example.baseballtalk.DTO.UserResponseDTO;
import com.example.baseballtalk.Entity.ChatEntity;
import com.example.baseballtalk.Entity.PostEntity;
import com.example.baseballtalk.Entity.UserEntity;
import com.example.baseballtalk.Repository.ChatRepository;
import com.example.baseballtalk.Repository.PostRepository;
import com.example.baseballtalk.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (post_repository.existsById(post_id)) {
            PostEntity post = post_repository.findById(post_id);

            int pageSize = 5;
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            Page<ChatEntity> chatbyPostPage = repository.findByPostOrderByCreatedAtDesc(pageRequest, post);
            List<ChatResponseDTO> dtos = chatbyPostPage.getContent().stream()
                    .map(entity -> new ChatResponseDTO(
                            entity.getId(),
                            entity.getContent(),
                            UserResponseDTO.of(entity.getAuthor()),
                            entity.getPost().getId(),
                            entity.getCreatedAt(),
                            entity.getUpdatedAt()))
                    .collect(Collectors.toList());
            return new PageImpl<>(dtos, pageRequest, chatbyPostPage.getTotalElements());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found.");
        }
    }

    public Page<ChatResponseDTO> getChatbyUser(int pageNo, int user_id) {
        if (user_repository.existsById(user_id)) {
            UserEntity author = user_repository.findById(user_id);

            int pageSize = 5;
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            Page<ChatEntity> chatbyUserPage = repository.findByAuthorOrderByCreatedAtDesc(pageRequest, author);
            List<ChatResponseDTO> dtos = chatbyUserPage.getContent().stream()
                    .map(entity -> new ChatResponseDTO(
                            entity.getId(),
                            entity.getContent(),
                            UserResponseDTO.of(entity.getAuthor()),
                            entity.getPost().getId(),
                            entity.getCreatedAt(),
                            entity.getUpdatedAt()))
                    .collect(Collectors.toList());
            return new PageImpl<>(dtos, pageRequest, chatbyUserPage.getTotalElements());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
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
        if (repository.existsById(id)) {
            ChatEntity entity = repository.findById(id);
            entity.setContent(dto.getContent());
            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat Not Found.");
        }
    }

    @Transactional
    public void deleteChat(int id) {
        repository.deleteById(id);
    }
}