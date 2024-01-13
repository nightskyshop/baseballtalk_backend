package com.example.test.Controller;

import com.example.test.DTO.ChatDTO;
import com.example.test.DTO.ChatResponseDTO;
import com.example.test.DTO.PostDTO;
import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.PostEntity;
import com.example.test.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService service;

    @GetMapping("/post/{post_id}")
    public Page<ChatResponseDTO> getChatbyPost(@RequestParam int pageNo, @PathVariable int post_id) { return service.getChatbyPost(pageNo, post_id); }

    @GetMapping("/user/{user_id}")
    public Page<ChatResponseDTO> getChatbyUser(@RequestParam int pageNo, @PathVariable int user_id) { return service.getChatbyUser(pageNo, user_id); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createChat(@RequestBody ChatDTO dto) {
        service.createChat(dto);
    }

    @PostMapping("/{id}")
    public void updateChat(@PathVariable int id, @RequestBody ChatDTO dto) { service.updateChat(id, dto); }
}