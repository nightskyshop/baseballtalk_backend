package com.example.test.Service;

import com.example.test.DTO.UserDTO;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserEntity getUser(int id) {
        return repository.findById(id);
    }

    public UserEntity createUser(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setImage(dto.getImage());
        entity.setTeam(dto.getTeam());
        return repository.save(entity);
    }

    public UserEntity updateUser(int id, UserDTO dto) {
        UserEntity entity = repository.findById(id);
        entity.setEmail(dto.getEmail());
        entity.setImage(dto.getImage());
        entity.setIntroduce(dto.getIntroduce());
        return repository.save(entity);
    }

    public void updatePassword(int id, UserDTO dto) {
        UserEntity entity = repository.findById(id);
        entity.setPassword(dto.getPassword());
        repository.save(entity);
    }
}