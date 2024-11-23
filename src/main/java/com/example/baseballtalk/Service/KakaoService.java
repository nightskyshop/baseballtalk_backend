package com.example.baseballtalk.Service;

import com.example.baseballtalk.DTO.*;
import com.example.baseballtalk.Entity.UserEntity;
import com.example.baseballtalk.JWT.TokenProvider;
import com.example.baseballtalk.Repository.UserRepository;
import com.example.baseballtalk.Service.oauth.CustomOAuth2User;
import com.example.baseballtalk.Service.oauth.CustomOAuth2UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.*;

import static com.example.baseballtalk.Config.Authority.ROLE_OAUTH;
import static com.example.baseballtalk.Config.Authority.ROLE_USER;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository user_repository;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";

    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    public TokenDTO getKakaoInfo(String code) throws Exception {
        if (code == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed get authorization code");

        String accessToken = "";
        String refreshToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", KAKAO_CLIENT_ID);
            params.add("client_secret", KAKAO_CLIENT_SECRET);
            params.add("redirect_uri", KAKAO_REDIRECT_URL);
            params.add("code", code);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
            System.out.println("httpEntity: " + httpEntity);

            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );


            JsonReader jsonReader = Json.createReader(new StringReader(response.getBody()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            accessToken = jsonObject.getString("access_token");
            refreshToken = jsonObject.getString("refresh_token");

            System.out.println("access_token :" + accessToken);
            System.out.println("refresh_token :" + refreshToken);
        } catch (Exception e) {
            throw e;
        }

        return getUserInfoWithToken(accessToken, refreshToken);
    }

    private TokenDTO getUserInfoWithToken(String accessToken, String refreshToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate restTem = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTem.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        JsonReader jsonReader = Json.createReader(new StringReader(response.getBody()));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        JsonObject account = jsonObject.getJsonObject("kakao_account");
        JsonObject profile = account.getJsonObject("profile");
        System.out.println("json: " + jsonObject);

        String email = String.valueOf(account.get("email")).replaceAll("\"", "");
        String nickname = String.valueOf(profile.get("nickname")).replaceAll("\"", "");
        String profileImgUrl = String.valueOf(profile.get("profile_image_url")).replaceAll("\"", "");

        KakaoDTO kakaoUser = new KakaoDTO(email, nickname, profileImgUrl);

        return saveKakao(kakaoUser, refreshToken);
    }

    public TokenDTO saveKakao(KakaoDTO kakaoUser, String refreshToken) {
        System.out.println("save User in member repo");
        Optional<UserEntity> existingUser = user_repository.findByEmail(kakaoUser.getEmail());
        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();
            user.setRefresh_token(refreshToken);

            Map<String,Object> attributes = new HashMap<>();
            attributes.put("email", kakaoUser.getEmail());
            attributes.put("id", user.getId());

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_OAUTH"));
            OAuth2User oAuth2User = new DefaultOAuth2User(
                    authorities,
                    attributes,
                    "email" // OAuth2User에서 사용하는 사용자 식별자 키
            );
            System.out.println(oAuth2User);

            String authorizedClientRegistrationId = "kakao";

            System.out.println("LOG1!!!!");

            OAuth2AuthenticationToken oAuth2AuthenticationToken = new OAuth2AuthenticationToken(oAuth2User, authorities, authorizedClientRegistrationId);
            System.out.println("LOG2!!!!");
            System.out.println(oAuth2AuthenticationToken);

            SecurityContextHolder.getContext().setAuthentication(oAuth2AuthenticationToken);
            System.out.println("LOG3!!!!");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            return tokenProvider.generateTokenDto(authentication);
        } else {
            UserRequestDTO userRequestDTO = new UserRequestDTO();
            userRequestDTO.setUsername(kakaoUser.getNickname());
            userRequestDTO.setEmail(kakaoUser.getEmail());
            userRequestDTO.setPassword("1111");
            userRequestDTO.setImage(kakaoUser.getProfileImgUrl());

            UserEntity newUser = userRequestDTO.toUser(passwordEncoder);
            newUser.setRefresh_token(refreshToken);
            newUser.setAuthority(ROLE_OAUTH);
            user_repository.save(newUser);

            Map<String,Object> attributes = new HashMap<>();
            attributes.put("email", kakaoUser.getEmail());
            attributes.put("id", newUser.getId());

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_OAUTH"));
            OAuth2User oAuth2User = new DefaultOAuth2User(
                    authorities,
                    attributes,
                    "email" // OAuth2User에서 사용하는 사용자 식별자 키
            );
            System.out.println(oAuth2User);

            String authorizedClientRegistrationId = "kakao";

            System.out.println("LOG1!!!!");

            OAuth2AuthenticationToken oAuth2AuthenticationToken = new OAuth2AuthenticationToken(oAuth2User, authorities, authorizedClientRegistrationId);
            System.out.println("LOG2!!!!");
            System.out.println(oAuth2AuthenticationToken);

            SecurityContextHolder.getContext().setAuthentication(oAuth2AuthenticationToken);
            System.out.println("LOG3!!!!");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            return tokenProvider.generateTokenDto(authentication);
        }
    }
}
