package com.example.baseballtalk.Controller;

import com.example.baseballtalk.DTO.ChatDTO;
import com.example.baseballtalk.DTO.ChatResponseDTO;
import com.example.baseballtalk.Entity.ChatEntity;
import com.example.baseballtalk.JWT.TokenProvider;
import com.example.baseballtalk.Repository.ChatRepository;
import com.example.baseballtalk.Service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService service;
    @Autowired
    private ChatRepository chatRepository;
    private final TokenProvider tokenProvider;

    @GetMapping("/post/{post_id}")
    public Page<ChatResponseDTO> getChatbyPost(@RequestParam int pageNo, @PathVariable int post_id) { return service.getChatbyPost(pageNo, post_id); }

    @GetMapping("/user/{user_id}")
    public Page<ChatResponseDTO> getChatbyUser(@RequestParam int pageNo, @PathVariable int user_id) { return service.getChatbyUser(pageNo, user_id); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createChat(@RequestBody ChatDTO dto, @RequestHeader("Authorization") String accessToken) {
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 전달되지 않았습니다.");
        }

        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.");
        }

        service.createChat(dto);
    }

    @PatchMapping("/{id}")
    public void updateChat(@PathVariable int id, @RequestHeader("Authorization") String accessToken, @RequestBody ChatDTO dto) {
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 전달되지 않았습니다.");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

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

        ChatEntity chat = chatRepository.findById(id);

        if (tokenId != chat.getAuthor().getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        service.updateChat(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id, @RequestHeader("Authorization") String accessToken) {
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

        ChatEntity chat = chatRepository.findById(id);

        if (tokenId != chat.getAuthor().getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        service.deleteChat(id);
    }
}