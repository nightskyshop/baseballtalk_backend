package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    List<TeamEntity> findAllByOrderByRanknumAsc();
    TeamEntity findById(int id);
    TeamEntity findByTeamname(String teamname);
    TeamEntity findByTeamnameEn(String teamnameEn);
    boolean existsByTeamname(String teamname);
}
