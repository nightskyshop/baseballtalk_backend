package com.example.test.Repository;

import com.example.test.Entity.PitcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitcherRepository extends JpaRepository<PitcherEntity, Integer> {
    PitcherEntity findById(int id);
}
