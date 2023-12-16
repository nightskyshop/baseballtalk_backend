package com.example.test.Controller;

import DTO.PostDTO;
import com.example.test.Entity.PostEntity;
import com.example.test.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService service;

    @GetMapping("")
    public Iterable<PostEntity> getAllPost() {
        return service.getAllPost();
    }

    @GetMapping("/{id}")
    public PostEntity getPost(@PathVariable int id) {
        return service.getPost(id);
    }

    @PostMapping("/")
    public PostEntity createPost(@RequestBody PostDTO dto) {
        return service.createPost(dto);
    }

    @PatchMapping("/{id}")
    public PostEntity updatePost(@PathVariable int id, @RequestBody PostDTO dto) {
        return service.updatePost(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {
        service.deletePost(id);
    }
}
