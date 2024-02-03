package com.example.test.Controller;

import com.example.test.DTO.PostDTO;
import com.example.test.DTO.PostResponseDTO;
import com.example.test.Entity.PostEntity;
import com.example.test.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService service;

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
    public void createPost(@RequestBody PostDTO dto) {
        service.createPost(dto);
    }

    @PatchMapping("/{id}")
    public void updatePost(@PathVariable int id, @RequestBody PostDTO dto) {
        service.updatePost(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {
        service.deletePost(id);
    }
}
