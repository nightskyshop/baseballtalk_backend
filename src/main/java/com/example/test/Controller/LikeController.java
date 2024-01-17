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

    @GetMapping("")
    public boolean existLike(
            @RequestParam int user_id,
            @RequestParam int type,
            @RequestParam int post_id,
            @RequestParam int chat_id
    ) { return service.existLike(user_id, type, post_id, chat_id); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createLike(@RequestBody LikeDTO dto) { service.createLike(dto); }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{user_id}")
    public void deleteLike(
            @PathVariable int user_id,
            @RequestParam int type,
            @RequestParam int post_id,
            @RequestParam int chat_id
    ) { service.deleteLike(user_id, type, post_id, chat_id); }
}
