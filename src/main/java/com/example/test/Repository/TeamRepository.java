package com.example.test.Repository;

import com.example.test.Entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    TeamEntity findById(int id);
}
