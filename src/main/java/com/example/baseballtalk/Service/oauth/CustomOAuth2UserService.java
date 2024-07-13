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
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        if (userRequest == null) {
            throw new IllegalArgumentException("OAuth2UserRequest cannot be null");
        }
        System.out.println("NEWUSER!!!");
        OAuth2User oAuth2User;
        try {
            oAuth2User = super.loadUser(userRequest);
            if (oAuth2User == null) {
                throw new OAuth2AuthenticationException("Failed to load user from OAuth2 provider");
            }
            return oAuth2User;
        } catch (OAuth2AuthenticationException e) {
            // More detailed error logging
            System.out.println("OAuth2AuthenticationException: " + e.toString());
            throw e;
        } catch (Exception ex) {
            // More detailed error logging
            System.out.println("Exception occurred: " + ex.toString());
            throw new OAuth2AuthenticationException(String.valueOf(ex));
        }
    }
}

