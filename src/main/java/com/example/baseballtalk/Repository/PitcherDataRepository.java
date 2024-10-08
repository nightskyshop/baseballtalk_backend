package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.PitcherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitcherDataRepository extends JpaRepository<PitcherDataEntity, Integer> {
}
