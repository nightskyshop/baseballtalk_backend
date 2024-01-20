package com.example.test.Repository;

import com.example.test.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(int id);
    Optional<UserEntity> findByEmail(String email);
    boolean existsById(int id);
}
