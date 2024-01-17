package com.example.test.Repository;

import com.example.test.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(int id);
    boolean existsById(int id);
}
