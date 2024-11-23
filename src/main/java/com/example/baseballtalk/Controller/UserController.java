package com.example.baseballtalk.Controller;

import com.example.baseballtalk.Config.Authority;
import com.example.baseballtalk.DTO.PasswordDTO;
import com.example.baseballtalk.DTO.UserRequestDTO;
import com.example.baseballtalk.DTO.UserResponseDTO;
import com.example.baseballtalk.Entity.UserEntity;
import com.example.baseballtalk.JWT.TokenProvider;
import com.example.baseballtalk.Repository.UserRepository;
import com.example.baseballtalk.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

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

        // String userId = tokenProvider.getUserIdFromToken(token);
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().equals(new SimpleGrantedAuthority("ROLE_OAUTH"))){

        }
        System.out.println("userId: " + userId);

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
    public void updateUser(@PathVariable int id, @RequestHeader("Authorization") String accessToken, @RequestBody UserRequestDTO dto) {
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


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id, @RequestHeader("Authorization") String accessToken) {
//        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 전달되지 않았습니다.");
//        }
//
//        String token = accessToken.substring(7);
//        boolean isValid = tokenProvider.validateToken(token);
//
//        if (!isValid) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.");
//        }
//
//        String userId = tokenProvider.getUserIdFromToken(token);
//        int tokenId;
//
//        try {
//            tokenId = Integer.parseInt(userId);
//        } catch (Exception err) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자의 ID를 확인할 수 없습니다.");
//        }
//
//        if (tokenId != id) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
//        }

        service.deleteUser(id);
    }

}
