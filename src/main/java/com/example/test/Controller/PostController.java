package com.example.test.Controller;

import com.example.test.DTO.PostDTO;
import com.example.test.DTO.PostResponseDTO;
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
        System.out.println("get All Post");
        System.out.print(service.getAllPost());
        return service.getAllPost();
    }

    @GetMapping("/{id}")
    public PostResponseDTO getPost(@PathVariable int id) {
        return service.getPost(id);
    }

    @PostMapping("")
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
