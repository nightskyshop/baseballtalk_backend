package com.example.test.Repository;

import com.example.test.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    PostEntity findById(int id);
    void deleteById(int id);
}
