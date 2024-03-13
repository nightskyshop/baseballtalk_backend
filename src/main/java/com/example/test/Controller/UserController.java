package com.example.test.Controller;

import com.example.test.DTO.PasswordDTO;
import com.example.test.DTO.UserDTO;
import com.example.test.DTO.UserProfileDTO;
import com.example.test.DTO.UserResponseDTO;
import com.example.test.Entity.UserEntity;
import com.example.test.JWT.TokenProvider;
import com.example.test.Repository.UserRepository;
import com.example.test.Service.UserService;
import com.sun.net.httpserver.HttpsServer;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;
    private final TokenProvider tokenProvider;

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable int id) { return service.getUser(id); }

    @GetMapping("/my")
    public UserResponseDTO getMyUser(@RequestHeader("Authorization") String accessToken) {
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 전달되지 않았습니다.");
        }

        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.");
        }

        String userId = tokenProvider.getUserIdFromToken(token);
        int tokenId;

        try {
            tokenId = Integer.parseInt(userId);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자의 ID를 확인할 수 없습니다.");
        }

        if (repository.existsById(tokenId)) {
            UserEntity user = repository.findById(tokenId);

            return UserResponseDTO.of(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestHeader("Authorization") String accessToken, @RequestBody UserProfileDTO dto) {
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 전달되지 않았습니다.");
        }

        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.");
        }

        String userId = tokenProvider.getUserIdFromToken(token);
        int tokenId;

        try {
            tokenId = Integer.parseInt(userId);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자의 ID를 확인할 수 없습니다.");
        }

        if (tokenId != id) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        service.updateUser(id, dto);
    }

    @PatchMapping("/password")
    public void updatePassword(@RequestHeader("Authorization") String accessToken, @RequestBody PasswordDTO dto) {
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 전달되지 않았습니다.");
        }

        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.");
        }

        String userId = tokenProvider.getUserIdFromToken(token);
        int tokenId;

        try {
            tokenId = Integer.parseInt(userId);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자의 ID를 확인할 수 없습니다.");
        }

        service.updatePassword(tokenId, dto);
    }
}
