package com.example.test.Repository;

import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    PostEntity findById(int id);
    Page<PostEntity> findByAuthor(PageRequest pageRequest, UserEntity user);
    void deleteById(int id);
}
