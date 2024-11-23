package com.example.baseballtalk.Service.oauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private Map<String,Object> attributes;
    public CustomOAuth2User(Map<String,Object> attributes){
        this.attributes = attributes;
    }
    @Override
    public Map<String,Object> getAttributes(){
        return attributes;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getName(){
        return (String)attributes.get("id");
    }
}
