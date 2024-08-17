package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.HitterEntity;
import com.example.baseballtalk.Entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitterRepository  extends JpaRepository<HitterEntity, Integer> {

    Page<HitterEntity> findAllByNameContainingOrderByGameDesc(String name, PageRequest pageRequest);
    Page<HitterEntity> findAllByRankedOrderByAvgDesc(boolean ranked, PageRequest pageRequest);
    List<HitterEntity> findAllByTeam(TeamEntity team);
    HitterEntity findById(int id);
    HitterEntity findByNameAndTeam(String name, TeamEntity team);
    boolean existsByNameAndTeam(String name, TeamEntity team);
}
