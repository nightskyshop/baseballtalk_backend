package com.example.test.Service;

import com.example.test.DTO.PasswordDTO;
import com.example.test.DTO.UserDTO;
import com.example.test.DTO.UserRequestDTO;
import com.example.test.DTO.UserResponseDTO;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO getUser(int id) {
        if (repository.existsById(id)) {
            UserEntity entity = repository.findById(id);
            UserResponseDTO dto = UserResponseDTO.of(entity);
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
    }

    public void updateUser(int id, UserRequestDTO dto) {
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

    public void updatePassword(int id, PasswordDTO dto) {
        if (repository.existsById(id)) {
            UserEntity entity = repository.findById(id);
            if (passwordEncoder.matches(dto.getOldPassword(), entity.getPassword())) {
                UserDTO userDto = new UserDTO(
                        entity,
                        entity.getUsername(),
                        entity.getEmail(),
                        dto.getNewPassword(),
                        entity.getImage(),
                        entity.getIntroduce(),
                        entity.getTeam()
                );

                System.out.println(userDto.getPassword());

                repository.save(userDto.toUser(passwordEncoder));
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
    }
}