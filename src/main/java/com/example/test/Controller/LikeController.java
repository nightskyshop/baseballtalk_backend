package com.example.test.Controller;

import com.example.test.DTO.LikeDTO;
import com.example.test.Entity.ChatEntity;
import com.example.test.JWT.TokenProvider;
import com.example.test.Service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService service;
    private final TokenProvider tokenProvider;

    @GetMapping("")
    public boolean existLike(
            @RequestParam int user_id,
            @RequestParam int type,
            @RequestParam int post_id,
            @RequestParam int chat_id
    ) { return service.existLike(user_id, type, post_id, chat_id); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createLike(@RequestBody LikeDTO dto, @RequestHeader("Authorization") String accessToken) {
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 전달되지 않았습니다.");
        }

        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.");
        }

        service.createLike(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{user_id}")
    public void deleteLike(
            @PathVariable int user_id,
            @RequestParam int type,
            @RequestParam int post_id,
            @RequestParam int chat_id,
            @RequestHeader("Authorization") String accessToken
    ) {
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

        if (tokenId != user_id) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        service.deleteLike(user_id, type, post_id, chat_id);
    }
}
