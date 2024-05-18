package com.example.test.Repository;

import com.example.test.Entity.HitterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitterRepository  extends JpaRepository<HitterEntity, Integer> {
    HitterEntity findById(int id);
}
