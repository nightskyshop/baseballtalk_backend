package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(int id);
    Optional<UserEntity> findByEmail(String email);
    boolean existsById(int id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void deleteById(int id);
}
