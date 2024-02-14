package com.example.test.Repository;

import com.example.test.Entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository  extends JpaRepository<PlayerEntity, Integer> {
    PlayerEntity findById(int id);
}
