package com.example.test.Controller;

import com.example.test.DTO.ChatDTO;
import com.example.test.DTO.PostDTO;
import com.example.test.Entity.ChatEntity;
import com.example.test.Entity.PostEntity;
import com.example.test.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService service;

    @PostMapping("/create")
    public void createChat(@RequestBody ChatDTO dto) {
        service.createChat(dto);
    }

    @PostMapping("/update/{id}")
    public void updateChat(@PathVariable int id, @RequestBody ChatDTO dto) { service.updateChat(id, dto); }
}