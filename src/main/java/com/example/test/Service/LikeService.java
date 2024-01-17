package com.example.test.Service;

import com.example.test.DTO.LikeDTO;
import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.LikeEntity;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.ChatRepository;
import com.example.test.Repository.LikeRepository;
import com.example.test.Repository.PostRepository;
import com.example.test.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository repository;
    @Autowired
    private UserRepository user_repository;
    @Autowired
    private PostRepository post_repository;
    @Autowired
    private ChatRepository chat_repository;

    public boolean existLike(int user_id, int type, int post_id, int chat_id) {
        UserEntity user = user_repository.findById(user_id);

        if (type == 0) {
            PostEntity post = post_repository.findById(post_id);
            return repository.existsByUserAndPost(user, post);
        } else if (type == 1) {
            ChatEntity chat = chat_repository.findById(chat_id);
            return repository.existsByUserAndChat(user, chat);
        }

        return false;
    }

    public void createLike(LikeDTO dto) {
        LikeEntity entity = new LikeEntity();
        entity.setType(dto.getType());

        if (dto.getType() == 0) {
            PostEntity post = post_repository.findById(dto.getPost());
            entity.setPost(post);
        } else if (dto.getType() == 1) {
            ChatEntity chat = chat_repository.findById(dto.getChat());
            entity.setChat(chat);
        }

        UserEntity user = user_repository.findById(dto.getUser());
        entity.setUser(user);

        repository.save(entity);
    }

    @Transactional
    public void deleteLike(int user_id, int type, int post_id, int chat_id) {
        UserEntity user = user_repository.findById(user_id);

        if (type == 0) {
            PostEntity post = post_repository.findById(post_id);
            repository.deleteByUserAndPost(user, post);
        } else if (type == 1) {
            ChatEntity chat = chat_repository.findById(chat_id);
            repository.deleteByUserAndChat(user, chat);
        }
    }
}