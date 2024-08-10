package com.example.baseballtalk.Service.oauth;

import com.example.baseballtalk.JWT.TokenProvider;
import com.example.baseballtalk.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private KakaoUserService kakaoUserService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("LOAD USER!");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
            return processOAuth2User(oAuth2User);
        } catch (Exception ex) {
            throw new OAuth2AuthenticationException("throw1");
        }
    }

    private OAuth2User processOAuth2User(OAuth2User oAuth2User) {
        System.out.println("PROCESS USER!");
        return oAuth2User;
    }
}


