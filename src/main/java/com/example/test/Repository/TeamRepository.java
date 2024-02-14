package com.example.test.Repository;

import com.example.test.Entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    List<TeamEntity> findAllByOrderByRanknumDesc();
    TeamEntity findById(int id);
}
