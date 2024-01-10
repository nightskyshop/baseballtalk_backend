package com.example.test.Service;

import com.example.test.DTO.PostDTO;
import com.example.test.DTO.PostResponseDTO;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.PostRepository;
import com.example.test.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service


public class PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private UserRepository user_repository;

    public Page<PostResponseDTO> getAllPost(int pageNo) {
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<PostEntity> postAllPage = repository.findAll(pageRequest);
        List<PostResponseDTO> dtos = postAllPage.getContent().stream()
                .map(entity -> new PostResponseDTO(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getContent(),
                        entity.getTeam(),
                        entity.getCategory(),
                        entity.getAuthor().getId()))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, postAllPage.getTotalElements());
    }

    public Page<PostResponseDTO> getPostbyUser(int pageNo, int user_id) {
        UserEntity author = user_repository.findById(user_id);

        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<PostEntity> postbyUserPage = repository.findByAuthor(pageRequest, author);
        List<PostResponseDTO> dtos = postbyUserPage.getContent().stream()
                .map(entity -> new PostResponseDTO(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getContent(),
                        entity.getTeam(),
                        entity.getCategory(),
                        entity.getAuthor().getId()))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageRequest, postbyUserPage.getTotalElements());
    }

    public PostResponseDTO getPost(int id) {
        PostEntity post = repository.findById(id);
        PostResponseDTO dto = new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getTeam(),
                post.getCategory(),
                post.getAuthor().getId()
        );
        return dto;
    }

    public void createPost(PostDTO dto) {
        PostEntity entity = new PostEntity();
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setTeam(dto.getTeam());
        entity.setCategory(dto.getCategory());

        UserEntity user = user_repository.findById(dto.getAuthor());
        entity.setAuthor(user);
        entity.getAuthor().getId();

        repository.save(entity);
    }

    public void updatePost(int id, PostDTO dto) {
        if (repository.existsById(id)) {
            PostEntity entity = repository.findById(id);
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setTeam(dto.getTeam());
            entity.setCategory(dto.getCategory());
            repository.save(entity);
        }
    }

    public void deletePost(int id) {
        repository.deleteById(id);
    }
}
