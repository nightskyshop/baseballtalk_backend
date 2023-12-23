package com.example.test.Service;

import DTO.PostDTO;
import DTO.PostResponseDTO;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.PostRepository;
import com.example.test.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private UserRepository user_repository;

    public Iterable<PostEntity> getAllPost() {
       return repository.findAll();
    }

    public PostEntity getPost(int id) {
        return repository.findById(id);
    }

    public PostEntity createPost(PostDTO dto) {
        PostEntity entity = new PostEntity();
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setUid(dto.getAuthor());
        UserEntity user = user_repository.findById(dto.getAuthor());
        entity.setAuthor(user);
        repository.save(entity);
        return entity;
    }

    public PostEntity updatePost(int id, PostDTO dto) {
        if (repository.existsById(id)) {
            PostEntity entity = repository.findById(id);
            entity.setContent(dto.getContent());
            entity.setTitle(dto.getTitle());
            repository.save(entity);
            return entity;
        }
        return null;
    }

    public void deletePost(int id) {
        repository.deleteById(id);
    }
}
