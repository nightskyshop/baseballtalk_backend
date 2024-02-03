package com.example.test.Service;

import com.example.test.DTO.KakaoDTO;
import com.example.test.Entity.UserEntity;
import com.example.test.Repository.UserRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Objects;
import java.util.Optional;

@Service
public class KakaoService {
    @Autowired
    private UserRepository user_repository;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect_uri}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";

    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    public String getKakaoInfo(String code) throws Exception {
        if (code == null) throw new Exception("Failed get authorization code");

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
        getUserInfoWithToken(accessToken, refreshToken);

        return accessToken;
    }

    private UserEntity getUserInfoWithToken(String accessToken, String refreshToken) throws Exception {
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

    public UserEntity saveKakao(KakaoDTO kakaoUser, String refreshToken) {
        System.out.println("save User in member repo");
        Optional<UserEntity> existingUser = user_repository.findByEmail(kakaoUser.getEmail());
        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();
//            user.setUsername(kakaoUser.getNickname());
//            user.setImage(kakaoUser.getProfileImgUrl());
            user.setRefresh_token(refreshToken);
            return user_repository.save(user);
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setPassword("1111");
            newUser.setEmail(kakaoUser.getEmail());
            newUser.setUsername(kakaoUser.getNickname());
            newUser.setRefresh_token(refreshToken);
            newUser.setImage(kakaoUser.getProfileImgUrl());
            return user_repository.save(newUser);
        }
    }
}
