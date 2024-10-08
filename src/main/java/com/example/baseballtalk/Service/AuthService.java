package com.example.baseballtalk.Service;

import com.example.baseballtalk.DTO.*;
import com.example.baseballtalk.Entity.UserEntity;
import com.example.baseballtalk.JWT.TokenProvider;
import com.example.baseballtalk.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

import static com.example.baseballtalk.Config.Authority.ROLE_USER;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public UserResponseDTO signup(UserRequestDTO requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername()) || userRepository.existsByEmail(requestDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "이미 가입되어 있는 유저입니다.");
        }

        String password = requestDto.getPassword();
        String passwordRegex = "^(?=.*\\d)(?=.*[@$!%*?&])[\\d@$!%*?&]{8,}$";

        if (!Pattern.matches(passwordRegex, password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "비밀번호는 최소 8자 이상이며, 숫자와 특수문자(@$!%*?&)를 각각 하나 이상 포함해야 합니다.");
        }

        UserEntity user = requestDto.toUser(passwordEncoder);
        user.setAuthority(ROLE_USER);
        return UserResponseDTO.of(userRepository.save(user));
    }

    public TokenDTO login(LoginDTO requestDto) {
        System.out.println("login");
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
        System.out.println("login1");
        System.out.println(authenticationToken);
        Authentication authentication;

        if (passwordEncoder.matches(requestDto.getPassword(), userRepository.findByEmail(requestDto.getEmail()).get().getPassword())) {
            System.out.println("ddd");
        }

        try {
            authentication = managerBuilder.getObject().authenticate(authenticationToken);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 혹은 비밀번호가 틀립니다.");
        }

        System.out.println(authentication);
        System.out.println("login2");
        return tokenProvider.generateTokenDto(authentication);
    }
}