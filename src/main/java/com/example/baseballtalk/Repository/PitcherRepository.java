package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.PitcherEntity;
import com.example.baseballtalk.Entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitcherRepository extends JpaRepository<PitcherEntity, Integer> {
    PitcherEntity findById(int id);
    PitcherEntity findByNameAndTeam(String name, TeamEntity team);
    Page<PitcherEntity> findAllByOrderByEraAsc(PageRequest pageRequest);

    boolean existsByNameAndTeam(String name, TeamEntity team);
}
