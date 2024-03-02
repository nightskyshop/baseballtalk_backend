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
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);
        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
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
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);
        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String userId = tokenProvider.getUserIdFromToken(accessToken);
        int tokenId;

        try {
            tokenId = Integer.parseInt(userId);
        } catch (Exception err) {
            throw err;
        }

        if (tokenId != user_id) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        service.deleteLike(user_id, type, post_id, chat_id);
    }
}
