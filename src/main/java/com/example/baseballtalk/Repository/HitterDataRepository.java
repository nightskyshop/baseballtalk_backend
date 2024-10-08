package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.HitterDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitterDataRepository  extends JpaRepository<HitterDataEntity, Integer> {
}
