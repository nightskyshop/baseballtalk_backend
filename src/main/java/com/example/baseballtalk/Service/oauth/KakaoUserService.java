package com.example.baseballtalk.Service.oauth;

import com.example.baseballtalk.Entity.UserEntity;
import com.example.baseballtalk.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KakaoUserService {
    private static UserRepository userRepository;

    @Autowired
    public KakaoUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerOrUpdateKakaoUser(String email, String name) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseGet(UserEntity::new);

    }
}
