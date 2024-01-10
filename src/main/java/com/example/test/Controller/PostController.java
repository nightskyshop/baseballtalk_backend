package com.example.test.Controller;

import com.example.test.DTO.PostDTO;
import com.example.test.DTO.PostResponseDTO;
import com.example.test.Entity.PostEntity;
import com.example.test.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{id}")
    public PostResponseDTO getPost(@PathVariable int id) {
        return service.getPost(id);
    }

    @PostMapping("")
    public void createPost(@RequestBody PostDTO dto) {
        service.createPost(dto);
    }

    @PatchMapping("/{id}")
    public void updatePost(@PathVariable int id, @RequestBody PostDTO dto) {
        service.updatePost(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {
        service.deletePost(id);
    }
}
