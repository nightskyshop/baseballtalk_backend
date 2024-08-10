package com.example.baseballtalk.Service.oauth;

import com.example.baseballtalk.DTO.TokenDTO;
import com.example.baseballtalk.Entity.UserEntity;
import com.example.baseballtalk.JWT.TokenProvider;
import com.example.baseballtalk.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KakaoUserService {
    private static UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    public KakaoUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OAuth2User registerOrUpdateKakaoUser(String email, String name, OAuth2User oAuth2User) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseGet(UserEntity::new);

//        userRepository.save(userEntity);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        TokenDTO tokenDTO = tokenProvider.generateTokenDto(authentication);

        System.out.println("!!!!!!register "+tokenDTO.getAccessToken());

        oAuth2User.getAttributes().put("token", tokenDTO.getAccessToken());

        return oAuth2User;
    }
}
