package com.example.test.Repository;

import com.example.test.Entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    List<TeamEntity> findAllByOrderByRanknumAsc();
    TeamEntity findById(int id);
    TeamEntity findByTeamname(String teamname);
    boolean existsByTeamname(String teamname);
}
