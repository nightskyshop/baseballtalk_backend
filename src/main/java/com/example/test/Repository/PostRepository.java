package com.example.test.Repository;

import com.example.test.Entity.PostEntity;
import com.example.test.Entity.TeamEntity;
import com.example.test.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    Page<PostEntity> findAllByOrderByCreatedAtDesc(PageRequest pageRequest);
    Page<PostEntity> findByAuthorOrderByCreatedAtDesc(PageRequest pageRequest, UserEntity user);
    Page<PostEntity> findByTeamOrderByCreatedAtDesc(PageRequest pageRequest, TeamEntity team);
    PostEntity findById(int id);
    void deleteById(int id);
    boolean existsById(int id);
}
