package com.example.test.Service;

import com.example.test.DTO.UserDTO;
import com.example.test.DTO.UserResponseDTO;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.UserRepository;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserResponseDTO getUser(int id) {
        if (repository.existsById(id)) {
            UserEntity entity = repository.findById(id);
            UserResponseDTO dto = UserResponseDTO.of(entity);
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
    }

//    public UserResponseDTO getMyUserBySecurity() {
//        return repository.findById(SecurityUtil.getCurrentUserId())
//                .map(UserResponseDTO::of)
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
//    }

    public void updateUser(int id, UserDTO dto) {
        if (repository.existsById(id)) {
            UserEntity entity = repository.findById(id);
            entity.setUsername(dto.getUsername());
            entity.setEmail(dto.getEmail());
            entity.setImage(dto.getImage());
            entity.setIntroduce(dto.getIntroduce());
            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
    }

    public void updatePassword(int id, UserDTO dto) {
        if (repository.existsById(id)) {
            UserEntity entity = repository.findById(id);
            entity.setPassword(dto.getPassword());
            repository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
    }
}