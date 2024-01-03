package com.example.test.Service;

import com.example.test.DTO.PostDTO;
import com.example.test.DTO.PostResponseDTO;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.PostRepository;
import com.example.test.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@org.springframework.stereotype.Service
public class PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private UserRepository user_repository;

    public Iterable<PostEntity> getAllPost() {
       return repository.findAll();
    }

    public PostResponseDTO getPost(int id) {
        PostEntity post = repository.findById(id);
        PostResponseDTO dto = new PostResponseDTO();
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setTeam(post.getTeam());
        dto.setCategory(post.getCategory());

        HashMap<String, String> user = new HashMap<String, String>();
        user.put("id", String.valueOf(post.getAuthor_id()));
        user.put("username", post.getAuthor_username());
        user.put("image", post.getAuthor_image());
        dto.setAuthor(user);

        return dto;
    }

    public PostEntity createPost(PostDTO dto) {
        PostEntity entity = new PostEntity();
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setTeam(dto.getTeam());
        entity.setCategory(dto.getCategory());

        UserEntity user = user_repository.findById(dto.getAuthor());
        entity.setAuthor_id(user.getId());
        entity.setAuthor_username(user.getUsername());
        entity.setAuthor_image(user.getImage());
        entity.setAuthor(user);

        repository.save(entity);
        return entity;
    }

    public PostEntity updatePost(int id, PostDTO dto) {
        if (repository.existsById(id)) {
            PostEntity entity = repository.findById(id);
            entity.setContent(dto.getContent());
            entity.setTitle(dto.getTitle());
            entity.setTeam(dto.getTeam());
            entity.setCategory(dto.getCategory());
            repository.save(entity);
            return entity;
        }
        return null;
    }

    public void deletePost(int id) {
        repository.deleteById(id);
    }
}
