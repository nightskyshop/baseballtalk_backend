package com.example.test.Service;

import com.example.test.Entity.UserEntity;
import com.example.test.Repository.UserRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.json.JSONParser;
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

@Service
public class KakaoService {
//    @Autowired
//    private UserRepository user_repository;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
//    private String KAKAO_CLIENT_ID;
//    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
//    private String KAKAO_CLIENT_SECRET;
//    @Value("${spring.security.oauth2.client.registration.kakao.redirect_uri}")
//    private String KAKAO_REDIRECT_URL;
//
//    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
//    private final static String KAKAO_API_URI = "https://kapi.kakao.com";
//
//    public String getKakaoLogin() {
//        return KAKAO_API_URI + "/oauth/authorize"
//                + "?client_id=" + KAKAO_CLIENT_ID
//                + "&redirect_uri=" + KAKAO_REDIRECT_URL
//                + "&response_type=code";
//    }
//
//    public String getKakaoInfo(String code, HttpServletRequest request) throws Exception {
//        if (code == null) throw new Exception("Failed get authorization code");
//
//        String accessToken = "";
//        String refreshToken = "";
//
//        try {
//            HttpHeaders headers = new HttpHeaders();
//
//            headers.add("Content-type", "application/x-www-form-urlencoded");
//            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//            params.add("grant-type", "authorization_code");
//            params.add("client_id", KAKAO_CLIENT_ID);
//            params.add("client_secret", KAKAO_CLIENT_SECRET);
//            params.add("redirect_uri", KAKAO_REDIRECT_URL);
//            params.add("code", code);
//
//            RestTemplate restTemplate = new RestTemplate();
//            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
//            System.out.println("httpEntity: " + httpEntity);
//
//            ResponseEntity<String> response = restTemplate.exchange(
//                    KAKAO_AUTH_URI + "/oauth/token",
//                    HttpMethod.POST,
//                    httpEntity,
//                    String.class
//            );
//
//            System.out.println(response);
//
//            return "response;";
//
////            JSONParser jsonParser = new JSONParser();
////            JSONPObject jsonObj = (JSONPObject) jsonParser.parse(response.getBody());
//        } catch (Exception e) {
//            throw new Exception("API call failed");
//        }
//    }
}
