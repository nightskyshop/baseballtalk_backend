package com.example.baseballtalk.Repository;

import com.example.baseballtalk.Entity.MaxDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaxDataRepository extends JpaRepository<MaxDataEntity, Integer> {
    MaxDataEntity findById(int id);
}
