package com.example.test.Service;

import com.example.test.DTO.UserDTO;
import com.example.test.DTO.UserResponseDTO;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO signup(UserDTO requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "이미 가입되어 있는 유저입니다.");
        }
        UserEntity user = requestDto.toUser(passwordEncoder);
        return UserResponseDTO.of(userRepository.save(user));
    }
}
