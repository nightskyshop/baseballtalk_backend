package com.example.baseballtalk.Service;

import com.example.baseballtalk.DTO.MaxDataDTO;
import com.example.baseballtalk.Entity.MaxDataEntity;
import com.example.baseballtalk.Repository.MaxDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MaxDataService {
    @Autowired
    private MaxDataRepository repository;

    public MaxDataEntity getMaxData() {
        try {
            MaxDataEntity entity = repository.findById(1);
            return entity;
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Max Data Not Found.");
        }
    }

    public void createOrUpdateMaxData(MaxDataDTO dto) {
        MaxDataEntity entity;
        if (repository.existsById(1)) {
            entity = repository.findById(1);
        } else {
            entity = new MaxDataEntity();
        }

        entity.setMaxHit(dto.getMaxHit());
        entity.setMaxHomerun(dto.getMaxHomerun());
        entity.setMaxRbi(dto.getMaxRbi());
        entity.setMaxStolenBase(dto.getMaxStolenBase());
        entity.setMaxWin(dto.getMaxWin());
        entity.setMaxInning(dto.getMaxInning());

        repository.save(entity);
    }
}
