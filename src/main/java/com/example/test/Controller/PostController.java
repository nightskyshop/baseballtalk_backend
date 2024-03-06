package com.example.test.Controller;

import com.example.test.DTO.PostDTO;
import com.example.test.DTO.PostResponseDTO;
import com.example.test.Entity.PostEntity;
import com.example.test.Entity.UserEntity;
import com.example.test.JWT.TokenProvider;
import com.example.test.Repository.PostRepository;
import com.example.test.Repository.UserRepository;
import com.example.test.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService service;
    @Autowired
    private PostRepository postRepository;
    private final TokenProvider tokenProvider;
    @GetMapping("")
    public Page<PostResponseDTO> getAllPost(@RequestParam int pageNo) {
        return service.getAllPost(pageNo);
    }

    @GetMapping("/user/{user_id}")
    public Page<PostResponseDTO> getPostbyUser(@RequestParam int pageNo, @PathVariable int user_id) { return service.getPostbyUser(pageNo, user_id); }

    @GetMapping("/team/{team_id}")
    public Page<PostResponseDTO> getPostbyTeam(@RequestParam int pageNo, @PathVariable int team_id) { return service.getPostbyTeam(pageNo, team_id); }

    @GetMapping("/{id}")
    public PostResponseDTO getPost(@PathVariable int id) {
        return service.getPost(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createPost(@RequestBody PostDTO dto, @RequestHeader("Authorization") String accessToken) {
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 전달되지 않았습니다.");
        }

        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.");
        }

        service.createPost(dto);
    }

    @PatchMapping("/{id}")
    public void updatePost(@PathVariable int id, @RequestHeader("Authorization") String accessToken, @RequestBody PostDTO dto) {
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

        PostEntity post = postRepository.findById(id);

        if (tokenId != post.getAuthor().getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        service.updatePost(id, dto);
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

        PostEntity post = postRepository.findById(id);

        if (tokenId != post.getAuthor().getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        service.deletePost(id);
    }
}
