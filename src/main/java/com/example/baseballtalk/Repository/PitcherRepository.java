package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.PitcherEntity;
import com.example.baseballtalk.Entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PitcherRepository extends JpaRepository<PitcherEntity, Integer> {
    PitcherEntity findById(int id);
    PitcherEntity findByNameAndTeam(String name, TeamEntity team);
    Page<PitcherEntity> findAllByRankedOrderByEraAsc(boolean ranked, PageRequest pageRequest);
    Page<PitcherEntity> findAllByNameContainingOrderByGameDesc(String name, PageRequest pageRequest);
    List<PitcherEntity> findAllByTeam(TeamEntity team);
    boolean existsByNameAndTeam(String name, TeamEntity team);
}
