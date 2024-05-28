package com.example.test.Service.oauth;

import com.example.test.Entity.UserEntity;
import com.example.test.Repository.UserRepository;
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
