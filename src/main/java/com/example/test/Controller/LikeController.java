package com.example.test.Controller;

import com.example.test.DTO.LikeDTO;
import com.example.test.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createLike(@RequestBody LikeDTO dto) { service.createLike(dto); }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("")
    public void deleteLike(@RequestBody LikeDTO dto) { service.deleteLike(dto); }
}
