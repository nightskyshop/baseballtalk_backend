package com.example.baseballtalk.Service.oauth;

import com.example.baseballtalk.JWT.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private TokenProvider tokenProvider;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            Object kakaoAccount = oauthToken.getPrincipal().getAttribute("kakao_account");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonReader jsonReader = Json.createReader(new StringReader(objectMapper.writeValueAsString(kakaoAccount)));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();


            System.out.println("principal " + oauthToken.getPrincipal());

            System.out.println("oauthToken " + oauthToken);
            System.out.println(jsonObject.get("email"));
        }
    }
}
