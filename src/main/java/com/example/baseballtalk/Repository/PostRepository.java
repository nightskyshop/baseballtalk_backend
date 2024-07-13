package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.PostEntity;
import com.example.baseballtalk.Entity.TeamEntity;
import com.example.baseballtalk.Entity.UserEntity;
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
