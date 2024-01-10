package com.example.test.Controller;

import com.example.test.DTO.LikeDTO;
import com.example.test.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService service;

    @PostMapping("/create")
    public void createLike(@RequestBody LikeDTO dto) { service.createLike(dto); }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable int id) { service.deleteLike(id); }
}
